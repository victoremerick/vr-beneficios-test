package br.com.vrbeneficios.transacao.service;

import br.com.vrbeneficios.cartao.application.exception.CartaoInexistenteException;
import br.com.vrbeneficios.cartao.util.AutorizacaoTestFactory;
import br.com.vrbeneficios.cartao.util.AutorizacaoTestHelper;
import br.com.vrbeneficios.transacao.application.exception.TransacaoSaldoInsuficienteException;
import br.com.vrbeneficios.transacao.application.exception.TransacaoSenhaIncorretaException;
import br.com.vrbeneficios.transacao.util.TransacaoTestFactory;
import br.com.vrbeneficios.util.ApplicationConfigIT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@DisplayName("Transacao - Service")
public class TransacaoApplicationServiceIT extends ApplicationConfigIT {

    @Test
    @DisplayName("Deve realizar transacao")
    public void deveCriarCartao(){

        var cartaoCriado = AutorizacaoTestFactory.umCartao();
        AutorizacaoTestHelper.persistir(repository, cartaoCriado);

        var requestTransacao = TransacaoTestFactory.umAutorizarCartaoRequestValido();
        transacaoApplicationService.handle(requestTransacao);

        var cartao = repository.selecionar(cartaoCriado.getNumero()).get();

        Assertions.assertEquals(cartao.getNumero(), cartaoCriado.getNumero());
        Assertions.assertEquals(cartao.getSenha(), cartaoCriado.getSenha());
        Assertions.assertEquals(cartao.getSaldo(), 500-requestTransacao.getValor());
    }

    @Test
    @DisplayName("Nao deve realizar a transacao")
    public void naoDeveRealizarTransacaoPorSaldoInsuficiente(){

        var cartao = AutorizacaoTestFactory.umCartao();
        AutorizacaoTestHelper.persistir(repository, cartao);

        var requestTransacao = TransacaoTestFactory.umAutorizarCartaoRequestSaldoAlto();

        var thrw = catchThrowable(() -> {transacaoApplicationService.handle(requestTransacao);});

        assertThat(thrw).isInstanceOf(TransacaoSaldoInsuficienteException.class);
    }

    @Test
    @DisplayName("Nao deve realizar transacao por senha invalida")
    public void naoDeveRealizarTransacaoPorSenhaInvalida(){

        var cartao = AutorizacaoTestFactory.umCartao();
        AutorizacaoTestHelper.persistir(repository, cartao);

        var requestTransacao = TransacaoTestFactory.umAutorizarCartaoRequestSenhaInvalida();

        var thrw = catchThrowable(() -> {transacaoApplicationService.handle(requestTransacao);});

        assertThat(thrw).isInstanceOf(TransacaoSenhaIncorretaException.class);
    }

    @Test
    @DisplayName("Nao deve realizar transacao por cartao inexistente")
    public void naoDeveRealizarTransacaoPorCartaoInexistente(){

        var requestTransacao = TransacaoTestFactory.umAutorizarCartaoRequestNumeroIncorreto();

        var thrw = catchThrowable(() -> {transacaoApplicationService.handle(requestTransacao);});

        assertThat(thrw).isInstanceOf(CartaoInexistenteException.class);
    }
}
