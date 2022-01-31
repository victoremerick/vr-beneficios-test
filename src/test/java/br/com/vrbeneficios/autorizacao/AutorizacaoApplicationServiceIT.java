package br.com.vrbeneficios.autorizacao;

import br.com.vrbeneficios.util.ApplicationConfigIT;
import br.com.vrbeneficios.util.ControllerConfigIT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Autorizacao - Service")
public class AutorizacaoApplicationServiceIT extends ApplicationConfigIT {

    @Test
    @DisplayName("Criar cartao")
    public void CriarCartao(){
        var cartao = service.handle();
        Assertions.assertNotNull(cartao.getNumero());
        Assertions.assertNotNull(cartao.getSenha());
    }
}
