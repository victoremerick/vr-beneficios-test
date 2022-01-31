package br.com.vrbeneficios.autorizacao;

import br.com.vrbeneficios.autorizacao.api.dto.request.CriarCartaoRequest;
import br.com.vrbeneficios.autorizacao.api.dto.response.CartaoCriadoResponse;
import br.com.vrbeneficios.autorizacao.application.model.Cartao;

public class AutorizacaoTestFactory {

    public static CartaoCriadoResponse umCartaoCriadoResponse(){
        return CartaoCriadoResponse.builder().numero("1111222233334444").senha("1111").build();
    }

    public static CriarCartaoRequest umCriarCartaoRequest(){
        return CriarCartaoRequest.builder().numero("1111222233334444").senha("1111").build();
    }

    public static Cartao umCartao(){
        return Cartao.builder().numero("1111222233334444").senha("1111").build();
    }
}
