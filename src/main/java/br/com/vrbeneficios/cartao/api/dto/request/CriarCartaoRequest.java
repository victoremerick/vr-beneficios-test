package br.com.vrbeneficios.cartao.api.dto.request;

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
}
