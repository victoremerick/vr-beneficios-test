package br.com.vrbeneficios.cartao.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "Cartao")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cartao {
    @Id
    private String numero;
    private String senha;
    private double saldo;
}
