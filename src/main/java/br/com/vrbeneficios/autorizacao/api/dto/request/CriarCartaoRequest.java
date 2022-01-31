package br.com.vrbeneficios.autorizacao.api.dto.request;

import br.com.vrbeneficios.autorizacao.api.dto.response.CartaoCriadoResponse;
import br.com.vrbeneficios.autorizacao.application.model.Cartao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarCartaoRequest {
    private String numero;
    private String senha;

    public static CriarCartaoRequest from(Cartao cartao){
        return CriarCartaoRequest.builder()
                .numero(cartao.getNumero())
                .senha(cartao.getSenha())
                .build();
    }
}
