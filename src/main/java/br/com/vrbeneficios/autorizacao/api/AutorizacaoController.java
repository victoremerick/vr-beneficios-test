package br.com.vrbeneficios.autorizacao.api;

import br.com.vrbeneficios.autorizacao.api.dto.request.CriarCartaoRequest;
import br.com.vrbeneficios.autorizacao.api.dto.response.CartaoCriadoResponse;
import br.com.vrbeneficios.autorizacao.application.CartaoApplicationService;
import br.com.vrbeneficios.autorizacao.application.exception.CartaoJaCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("cartoes")
public class AutorizacaoController {

    @Autowired
    private CartaoApplicationService service;

    @PostMapping
    public ResponseEntity<CartaoCriadoResponse> incluir(@RequestBody CriarCartaoRequest request){
        try {
            var dto = service.handle(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        }catch(CartaoJaCadastradoException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(CartaoCriadoResponse.from(request));
        }
    }
}
