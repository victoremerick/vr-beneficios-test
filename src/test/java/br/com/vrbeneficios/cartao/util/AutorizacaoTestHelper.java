package br.com.vrbeneficios.cartao.util;

import br.com.vrbeneficios.cartao.api.dto.request.CriarCartaoRequest;
import br.com.vrbeneficios.cartao.application.model.Cartao;
import br.com.vrbeneficios.cartao.database.CartaoRepositoryService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static br.com.vrbeneficios.util.TestUtils.objectToJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class AutorizacaoTestHelper {

    public static MvcResult incluir(MockMvc mock, ResultMatcher resultadoEsperado, CriarCartaoRequest request) throws Exception {
        return mock.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectToJson(request)))
                .andExpect(resultadoEsperado).andReturn();
    }

    public static MvcResult getSaldo(MockMvc mock, ResultMatcher resultadoEsperado, String numeroCartao) throws Exception {
        return mock.perform(get("/cartoes/"+numeroCartao)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(resultadoEsperado).andReturn();
    }

    public static void persistir(CartaoRepositoryService repository, Cartao cartao){
        repository.persistir(cartao);
    }
}
