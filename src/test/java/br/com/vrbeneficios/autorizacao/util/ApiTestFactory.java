package br.com.vrbeneficios.autorizacao.util;

import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static br.com.vrbeneficios.util.TestUtils.objectToJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static java.util.Objects.nonNull;

public class ApiTestFactory {
    public static MvcResult incluir(MockMvc mock, ResultMatcher resultadoEsperado) throws Exception {
        return mock.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(""))
                .andExpect(resultadoEsperado).andReturn();
    }
}
