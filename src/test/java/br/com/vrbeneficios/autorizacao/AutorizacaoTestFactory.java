package br.com.vrbeneficios.autorizacao;

import br.com.vrbeneficios.autorizacao.api.dto.response.CartaoCriadoDto;

public class AutorizacaoTestFactory {
    public static CartaoCriadoDto umCartaoCriadoDto(){
        return CartaoCriadoDto.builder().numero("1111222233334444").senha("1111").build();
    }
}
