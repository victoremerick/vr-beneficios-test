package br.com.vrbeneficios.util;

import br.com.vrbeneficios.cartao.application.CartaoApplicationService;
import br.com.vrbeneficios.cartao.database.CartaoRepositoryService;
import br.com.vrbeneficios.transacao.application.TransacaoApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
public class ControllerConfigIT {

    protected ObjectMapper mapper = new ObjectMapper();

    @Autowired
    protected MockMvc mock;

    @MockBean
    protected CartaoApplicationService cartaoApplicationService;

    @MockBean
    protected TransacaoApplicationService transacaoApplicationService;

    @Autowired
    protected CartaoRepositoryService repository;
}
