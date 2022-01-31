package br.com.vrbeneficios.autorizacao.database;

import br.com.vrbeneficios.autorizacao.application.model.Cartao;
import br.com.vrbeneficios.autorizacao.database.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartaoRepositoryService {

    @Autowired
    private CartaoRepository repository;

    public Cartao persistir(Cartao cartao){
        return repository.save(cartao);
    }

    public Optional<Cartao> selecionar(String numeroCartao){
        return repository.findById(numeroCartao);
    }
}
