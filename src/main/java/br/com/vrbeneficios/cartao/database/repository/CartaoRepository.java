package br.com.vrbeneficios.cartao.database.repository;

import br.com.vrbeneficios.cartao.application.model.Cartao;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartaoRepository extends CrudRepository<Cartao, String> {

    public Optional<Cartao> findByNumeroAndAndSenhaAndAndSaldoGreaterThanEqual(String numero, String senha, double saldo);

}
