package br.com.vrbeneficios.autorizacao.database.repository;

import br.com.vrbeneficios.autorizacao.application.model.Cartao;
import org.springframework.data.repository.CrudRepository;

public interface CartaoRepository extends CrudRepository<Cartao, String> {
}
