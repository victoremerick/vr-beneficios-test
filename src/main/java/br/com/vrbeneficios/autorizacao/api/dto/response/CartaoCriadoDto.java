package br.com.vrbeneficios.autorizacao.api.dto.response;

import br.com.vrbeneficios.autorizacao.application.model.Cartao;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaoCriadoDto {

    private String numero;
    private String senha;

    public static CartaoCriadoDto from(Cartao cartao){
        return CartaoCriadoDto.builder()
                .numero(cartao.getNumero())
                .senha(cartao.getSenha())
                .build();
    }
}
