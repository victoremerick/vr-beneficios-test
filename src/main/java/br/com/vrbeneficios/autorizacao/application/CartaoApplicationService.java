package br.com.vrbeneficios.autorizacao.application;

import br.com.vrbeneficios.autorizacao.Util;
import br.com.vrbeneficios.autorizacao.api.dto.response.CartaoCriadoDto;
import br.com.vrbeneficios.autorizacao.application.model.Cartao;
import br.com.vrbeneficios.autorizacao.database.CartaoRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoApplicationService {

    @Autowired
    private CartaoRepositoryService repositoryService;

    public CartaoCriadoDto handle(){
        var cartao = Cartao.builder()
                .numero(Util.gerarNumeroCartao())
                .saldo(500)
                .senha(Util.numeroAleatorio4Digitos())
                .build();

        cartao = repositoryService.persistir(cartao);

        return CartaoCriadoDto.from(cartao);
    }
}
