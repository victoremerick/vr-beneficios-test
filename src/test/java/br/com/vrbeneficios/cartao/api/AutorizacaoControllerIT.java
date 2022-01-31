package br.com.vrbeneficios.cartao.api;

import br.com.vrbeneficios.cartao.application.exception.CartaoInexistenteException;
import br.com.vrbeneficios.cartao.application.exception.CartaoJaCadastradoException;
import br.com.vrbeneficios.cartao.util.AutorizacaoTestFactory;
import br.com.vrbeneficios.cartao.util.AutorizacaoTestHelper;
import br.com.vrbeneficios.util.ControllerConfigIT;
import br.com.vrbeneficios.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Cartao - API")
public class AutorizacaoControllerIT extends ControllerConfigIT {
    @Test
    @DisplayName("Criar cartao e retornar os dados de senha e numero cadastrado")
    public void CriarCartao() throws Exception {
        var cartaoResquest = AutorizacaoTestFactory.umCriarCartaoRequest();
        AutorizacaoTestHelper.incluir(mock, status().isCreated(), cartaoResquest).getResponse();
        Mockito.verify(cartaoApplicationService).handle(cartaoResquest);
    }

    @Test
    @DisplayName("Nao deve criar cartao e retornar os dados de senha e numero da solicitacao")
    public void NaoDeveCriarCartao() throws Exception {
        var cartaoResquest = AutorizacaoTestFactory.umCriarCartaoRequest();
        Mockito.when(cartaoApplicationService.handle(cartaoResquest)).thenThrow(new CartaoJaCadastradoException());
        var result = AutorizacaoTestHelper.incluir(mock, status().isUnprocessableEntity(), cartaoResquest).getResponse();
        var response = result.getContentAsString();
        Assertions.assertEquals(response, TestUtils.objectToJson(cartaoResquest));
    }
    @Test
    @DisplayName("Nao deve retornar o saldo do cartao")
    public void naoDeveRetornarSaldo() throws Exception {
        var cartaoResquest = AutorizacaoTestFactory.umCriarCartaoRequest();
        Mockito.when(cartaoApplicationService.handle(cartaoResquest.getNumero())).thenThrow(new CartaoInexistenteException());
        AutorizacaoTestHelper.getSaldo(mock, status().isNotFound(), cartaoResquest.getNumero()).getResponse();
        Mockito.verify(cartaoApplicationService).handle(cartaoResquest.getNumero());
    }

    @Test
    @DisplayName("Deve retornar o saldo do cartao")
    public void deveRetornarSaldo() throws Exception {
        var cartao = AutorizacaoTestFactory.umCartao();
        Mockito.when(cartaoApplicationService.handle(cartao.getNumero())).thenReturn(cartao.getSaldo());
        var result = AutorizacaoTestHelper.getSaldo(mock, status().isOk(), cartao.getNumero()).getResponse();
        var response = result.getContentAsString();
        Assertions.assertEquals(response, String.format("%.2f", cartao.getSaldo()));
    }
}
