package br.com.vrbeneficios.autorizacao.util;

import br.com.vrbeneficios.autorizacao.api.dto.request.CriarCartaoRequest;
import br.com.vrbeneficios.autorizacao.application.model.Cartao;
import br.com.vrbeneficios.autorizacao.database.CartaoRepositoryService;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static br.com.vrbeneficios.util.TestUtils.objectToJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static java.util.Objects.nonNull;

public class ApiTestFactory {

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
