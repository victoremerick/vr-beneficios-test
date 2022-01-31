package br.com.vrbeneficios.cartao.api.dto.response;

import br.com.vrbeneficios.cartao.api.dto.request.CriarCartaoRequest;
import br.com.vrbeneficios.cartao.application.model.Cartao;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class CartaoCriadoResponse {

    private String numero;
    private String senha;

    public static CartaoCriadoResponse from(Cartao cartao){
        return CartaoCriadoResponse.builder()
                .numero(cartao.getNumero())
                .senha(cartao.getSenha())
                .build();
    }

    public static CartaoCriadoResponse from(CriarCartaoRequest cartao){
        return CartaoCriadoResponse.builder()
                .numero(cartao.getNumero())
                .senha(cartao.getSenha())
                .build();
    }
}
