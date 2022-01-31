package br.com.vrbeneficios.autorizacao;

import br.com.vrbeneficios.autorizacao.api.dto.response.CartaoCriadoResponse;
import br.com.vrbeneficios.autorizacao.application.exception.CartaoInexistenteException;
import br.com.vrbeneficios.autorizacao.application.exception.CartaoJaCadastradoException;
import br.com.vrbeneficios.autorizacao.util.ApiTestFactory;
import br.com.vrbeneficios.util.ControllerConfigIT;
import br.com.vrbeneficios.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Autorizacao - API")
public class AutorizacaoControllerIT extends ControllerConfigIT {
    @Test
    @DisplayName("Criar cartao e retornar os dados de senha e numero cadastrado")
    public void CriarCartao() throws Exception {
        var cartaoResquest = AutorizacaoTestFactory.umCriarCartaoRequest();
        ApiTestFactory.incluir(mock, status().isCreated(), cartaoResquest).getResponse();
        Mockito.verify(service).handle(cartaoResquest);
    }

    @Test
    @DisplayName("Nao deve criar cartao e retornar os dados de senha e numero da solicitacao")
    public void NaoDeveCriarCartao() throws Exception {
        var cartaoResquest = AutorizacaoTestFactory.umCriarCartaoRequest();
        Mockito.when(service.handle(cartaoResquest)).thenThrow(new CartaoJaCadastradoException());
        var result = ApiTestFactory.incluir(mock, status().isUnprocessableEntity(), cartaoResquest).getResponse();
        var response = result.getContentAsString();
        Assertions.assertEquals(response, TestUtils.objectToJson(cartaoResquest));
    }
    @Test
    @DisplayName("Nao deve retornar o saldo do cartao")
    public void naoDeveRetornarSaldo() throws Exception {
        var cartaoResquest = AutorizacaoTestFactory.umCriarCartaoRequest();
        Mockito.when(service.handle(cartaoResquest.getNumero())).thenThrow(new CartaoInexistenteException());
        ApiTestFactory.getSaldo(mock, status().isNotFound(), cartaoResquest.getNumero()).getResponse();
        Mockito.verify(service).handle(cartaoResquest.getNumero());
    }

    @Test
    @DisplayName("Deve retornar o saldo do cartao")
    public void deveRetornarSaldo() throws Exception {
        var cartao = AutorizacaoTestFactory.umCartao();
        ApiTestFactory.persistir(repository, cartao);

        var result = ApiTestFactory.getSaldo(mock, status().isOk(), cartao.getNumero()).getResponse();
        var response = result.getContentAsString();
        Assertions.assertEquals(response, String.format("%.2f", cartao.getSaldo()));
    }
}
