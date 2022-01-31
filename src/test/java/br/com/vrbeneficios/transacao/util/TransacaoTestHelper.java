package br.com.vrbeneficios.transacao.util;

import br.com.vrbeneficios.transacao.api.dto.request.AutorizarCartaoRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static br.com.vrbeneficios.util.TestUtils.objectToJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class TransacaoTestHelper {

    public static MvcResult autorizarTransacao(MockMvc mock, ResultMatcher resultadoEsperado, AutorizarCartaoRequest request) throws Exception {
        return mock.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectToJson(request)))
                .andExpect(resultadoEsperado).andReturn();
    }
}
