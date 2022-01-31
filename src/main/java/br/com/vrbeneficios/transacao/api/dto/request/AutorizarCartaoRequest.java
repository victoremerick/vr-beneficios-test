package br.com.vrbeneficios.transacao.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutorizarCartaoRequest {
    private String numero;
    private String senha;
    private double valor;
}
