package br.com.vrbeneficios.cartao.service;

import br.com.vrbeneficios.cartao.application.exception.CartaoInexistenteException;
import br.com.vrbeneficios.cartao.application.exception.CartaoJaCadastradoException;
import br.com.vrbeneficios.cartao.util.AutorizacaoTestFactory;
import br.com.vrbeneficios.cartao.util.AutorizacaoTestHelper;
import br.com.vrbeneficios.util.ApplicationConfigIT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Cartao - Service")
public class AutorizacaoApplicationServiceIT extends ApplicationConfigIT {

    @Test
    @DisplayName("Deve criar cartao")
    public void deveCriarCartao(){

        var request = AutorizacaoTestFactory.umCriarCartaoRequest();

        var cartao = cartaoApplicationService.handle(request);
        Assertions.assertNotNull(cartao.getNumero());
        Assertions.assertNotNull(cartao.getSenha());
    }

    @Test
    @DisplayName("Nao deve criar cartao")
    public void naoDeveCriarCartao(){

        var cartao =  AutorizacaoTestFactory.umCartao();
        AutorizacaoTestHelper.persistir(repository, cartao);

        var request = AutorizacaoTestFactory.umCriarCartaoRequest();

        var thrw = catchThrowable(() -> {cartaoApplicationService.handle(request);});

        assertThat(thrw).isInstanceOf(CartaoJaCadastradoException.class);
    }

    @Test
    @DisplayName("Nao deve retornar saldo")
    public void naoDeveRetornarSaldo(){

        var request = AutorizacaoTestFactory.umCriarCartaoRequest();

        var thrw = catchThrowable(() -> {cartaoApplicationService.handle(request.getNumero());});
        assertThat(thrw).isInstanceOf(CartaoInexistenteException.class);
    }

    @Test
    @DisplayName("Deve retornar saldo do cartao")
    public void deveeRetornarSaldoCartao(){

        var cartao =  AutorizacaoTestFactory.umCartao();
        AutorizacaoTestHelper.persistir(repository, cartao);

        var saldo = cartaoApplicationService.handle(cartao.getNumero());
        Assertions.assertEquals(saldo, cartao.getSaldo());
    }
}
