package br.com.vrbeneficios.autorizacao;

import br.com.vrbeneficios.autorizacao.api.dto.response.CartaoCriadoDto;
import br.com.vrbeneficios.autorizacao.util.ApiTestFactory;
import br.com.vrbeneficios.util.ControllerConfigIT;
import br.com.vrbeneficios.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Autorizacao - API")
public class AutorizacaoControllerIT extends ControllerConfigIT {
    @Test
    @DisplayName("Criar cartao e retornar os dados de senha e numero")
    public void CriarCartao() throws Exception {

        var cartao = AutorizacaoTestFactory.umCartaoCriadoDto();

        Mockito.when(service.handle()).thenReturn(cartao);

        var result = ApiTestFactory.incluir(mock, status().isCreated()).getResponse();
        String response = result.getContentAsString();
        var object = TestUtils.jsonToObject(response, CartaoCriadoDto.class);
        Assertions.assertEquals(object.getNumero(), cartao.getNumero());
        Assertions.assertEquals(object.getSenha(), cartao.getSenha());
    }
}
