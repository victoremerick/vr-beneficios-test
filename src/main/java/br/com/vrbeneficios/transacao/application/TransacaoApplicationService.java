package br.com.vrbeneficios.transacao.application;

import br.com.vrbeneficios.cartao.application.exception.CartaoInexistenteException;
import br.com.vrbeneficios.transacao.api.dto.request.AutorizarCartaoRequest;
import br.com.vrbeneficios.cartao.database.CartaoRepositoryService;
import br.com.vrbeneficios.transacao.application.exception.TransacaoSaldoInsuficienteException;
import br.com.vrbeneficios.transacao.application.exception.TransacaoSenhaIncorretaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoApplicationService {

    @Autowired
    private CartaoRepositoryService repositoryService;

    public void handle(AutorizarCartaoRequest request){
        repositoryService.selecionar(request.getNumero()).ifPresentOrElse(
        cartao -> {
            if(!cartao.getSenha().equals(request.getSenha())){
                throw new TransacaoSenhaIncorretaException();
            }
            if(cartao.getSaldo() < request.getValor()){
                throw new TransacaoSaldoInsuficienteException();
            }
            cartao.setSaldo(cartao.getSaldo()-request.getValor());
            repositoryService.persistir(cartao);
        },
        () -> {
            throw new CartaoInexistenteException();
        });
    }
}
