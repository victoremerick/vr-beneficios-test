package br.com.vrbeneficios.autorizacao.api;

import br.com.vrbeneficios.autorizacao.api.dto.response.CartaoCriadoDto;
import br.com.vrbeneficios.autorizacao.application.CartaoApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("cartoes")
public class AutorizacaoController {

    @Autowired
    private CartaoApplicationService service;

    @PostMapping
    public ResponseEntity<CartaoCriadoDto> incluir(){
        var dto = service.handle();

        return ResponseEntity.status(201).body(dto);
    }
}
