package br.com.vrbeneficios.cartao.database;

import br.com.vrbeneficios.cartao.application.model.Cartao;
import br.com.vrbeneficios.cartao.database.repository.CartaoRepository;
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

    public Optional<Cartao> verificarCartao(String numero, String senha, double saldo){
        return repository.findByNumeroAndAndSenhaAndAndSaldoGreaterThanEqual(numero, senha, saldo);
    }
}
