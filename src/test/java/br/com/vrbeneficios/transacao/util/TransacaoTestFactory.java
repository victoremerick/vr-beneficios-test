package br.com.vrbeneficios.transacao.util;

import br.com.vrbeneficios.cartao.util.AutorizacaoTestFactory;
import br.com.vrbeneficios.transacao.api.dto.request.AutorizarCartaoRequest;

public class TransacaoTestFactory {
    public static AutorizarCartaoRequest umAutorizarCartaoRequestSaldoAlto(){

        var cartao = AutorizacaoTestFactory.umCartao();

        return AutorizarCartaoRequest.builder()
                .numero(cartao.getNumero())
                .senha(cartao.getSenha())
                .valor(600)
                .build();
    }

    public static AutorizarCartaoRequest umAutorizarCartaoRequestSenhaInvalida(){

        var cartao = AutorizacaoTestFactory.umCartao();

        return AutorizarCartaoRequest.builder()
                .numero(cartao.getNumero())
                .senha("1122")
                .valor(25)
                .build();
    }

    public static AutorizarCartaoRequest umAutorizarCartaoRequestNumeroIncorreto(){

        var cartao = AutorizacaoTestFactory.umCartao();

        return AutorizarCartaoRequest.builder()
                .numero("1122334455667788")
                .senha(cartao.getSenha())
                .valor(25)
                .build();
    }

    public static AutorizarCartaoRequest umAutorizarCartaoRequestValido(){

        var cartao = AutorizacaoTestFactory.umCartao();

        return AutorizarCartaoRequest.builder()
                .numero(cartao.getNumero())
                .senha(cartao.getSenha())
                .valor(25)
                .build();
    }
}
