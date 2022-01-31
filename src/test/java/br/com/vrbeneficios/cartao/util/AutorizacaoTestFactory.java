package br.com.vrbeneficios.cartao.util;

import br.com.vrbeneficios.cartao.api.dto.request.CriarCartaoRequest;
import br.com.vrbeneficios.cartao.application.model.Cartao;

public class AutorizacaoTestFactory {

    public static CriarCartaoRequest umCriarCartaoRequest(){
        return CriarCartaoRequest.builder().numero("1111222233334444").senha("1111").build();
    }

    public static Cartao umCartao(){
        return Cartao.builder().numero("1111222233334444").senha("1111").saldo(500).build();
    }
}
