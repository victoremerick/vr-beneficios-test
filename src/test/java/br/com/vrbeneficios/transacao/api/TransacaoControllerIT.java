package br.com.vrbeneficios.transacao.api;

import br.com.vrbeneficios.cartao.application.exception.CartaoInexistenteException;
import br.com.vrbeneficios.transacao.api.response.AutorizacaoResponse;
import br.com.vrbeneficios.transacao.application.exception.TransacaoSaldoInsuficienteException;
import br.com.vrbeneficios.transacao.application.exception.TransacaoSenhaIncorretaException;
import br.com.vrbeneficios.transacao.util.TransacaoTestFactory;
import br.com.vrbeneficios.transacao.util.TransacaoTestHelper;
import br.com.vrbeneficios.util.ControllerConfigIT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Transacao - API")
public class TransacaoControllerIT extends ControllerConfigIT {

    @Test
    @DisplayName("Não deve autorizar por causa de senha incorreta")
    public void naoDeveAutorizarPorSenhaIncorreta() throws Exception {
        var request = TransacaoTestFactory.umAutorizarCartaoRequestSenhaInvalida();
        Mockito.doThrow(new TransacaoSenhaIncorretaException()).when(transacaoApplicationService).handle(request);
        var result = TransacaoTestHelper.autorizarTransacao(mock, status().isUnprocessableEntity(), request).getResponse();
        Assertions.assertEquals(result.getContentAsString(), AutorizacaoResponse.SENHA_INVALIDA.toString());
        Mockito.verify(transacaoApplicationService).handle(request);
    }

    @Test
    @DisplayName("Não deve autorizar por causa de saldo insuficiente")
    public void naoDeveAutorizarPorSaldoInsuficiente() throws Exception {
        var request = TransacaoTestFactory.umAutorizarCartaoRequestSaldoAlto();
        Mockito.doThrow(new TransacaoSaldoInsuficienteException()).when(transacaoApplicationService).handle(request);
        var result = TransacaoTestHelper.autorizarTransacao(mock, status().isUnprocessableEntity(), request).getResponse();
        Assertions.assertEquals(result.getContentAsString(), AutorizacaoResponse.SALDO_INSUFICIENTE.toString());
        Mockito.verify(transacaoApplicationService).handle(request);
    }

    @Test
    @DisplayName("Não deve autorizar por causa de cartao inexistente")
    public void naoDeveAutorizarPorCartaoInexistente() throws Exception {
        var request = TransacaoTestFactory.umAutorizarCartaoRequestNumeroIncorreto();
        Mockito.doThrow(new CartaoInexistenteException()).when(transacaoApplicationService).handle(request);
        var result = TransacaoTestHelper.autorizarTransacao(mock, status().isUnprocessableEntity(), request).getResponse();
        Assertions.assertEquals(result.getContentAsString(), AutorizacaoResponse.CARTAO_INEXISTENTE.toString());
        Mockito.verify(transacaoApplicationService).handle(request);
    }

    @Test
    @DisplayName("Deve autorizar a transacao")
    public void deveAutorizarTransacao() throws Exception {
        var request = TransacaoTestFactory.umAutorizarCartaoRequestNumeroIncorreto();
        var result = TransacaoTestHelper.autorizarTransacao(mock, status().isOk(), request).getResponse();
        Assertions.assertEquals(result.getContentAsString(), AutorizacaoResponse.OK.toString());
        Mockito.verify(transacaoApplicationService).handle(request);
    }

}
