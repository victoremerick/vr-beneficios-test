package br.com.vrbeneficios.autorizacao.application;

import br.com.vrbeneficios.autorizacao.Util;
import br.com.vrbeneficios.autorizacao.api.dto.request.CriarCartaoRequest;
import br.com.vrbeneficios.autorizacao.api.dto.response.CartaoCriadoResponse;
import br.com.vrbeneficios.autorizacao.application.exception.CartaoInexistenteException;
import br.com.vrbeneficios.autorizacao.application.exception.CartaoJaCadastradoException;
import br.com.vrbeneficios.autorizacao.application.model.Cartao;
import br.com.vrbeneficios.autorizacao.database.CartaoRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoApplicationService {

    @Autowired
    private CartaoRepositoryService repositoryService;

    public CartaoCriadoResponse handle(CriarCartaoRequest request){
        repositoryService.selecionar(request.getNumero()).ifPresent(cartao -> {throw new CartaoJaCadastradoException();});
        var cartao = Cartao.builder()
                .numero(Util.gerarNumeroCartao())
                .saldo(500)
                .senha(Util.numeroAleatorio4Digitos())
                .build();

        cartao = repositoryService.persistir(cartao);
        return CartaoCriadoResponse.from(cartao);
    }

    public Double handle(String numeroCartao){
        var cartao = repositoryService.selecionar(numeroCartao)
                .orElseThrow(CartaoInexistenteException::new);
        return cartao.getSaldo();
    }
}
