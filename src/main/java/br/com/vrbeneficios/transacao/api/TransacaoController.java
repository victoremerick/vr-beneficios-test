package br.com.vrbeneficios.transacao.api;

import br.com.vrbeneficios.cartao.application.exception.CartaoInexistenteException;
import br.com.vrbeneficios.transacao.api.dto.request.AutorizarCartaoRequest;
import br.com.vrbeneficios.transacao.api.response.AutorizacaoResponse;
import br.com.vrbeneficios.transacao.application.TransacaoApplicationService;
import br.com.vrbeneficios.transacao.application.exception.TransacaoSaldoInsuficienteException;
import br.com.vrbeneficios.transacao.application.exception.TransacaoSenhaIncorretaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoApplicationService service;

    @PostMapping
    public ResponseEntity<String> autorizar(@RequestBody AutorizarCartaoRequest request){

        var retorno = AutorizacaoResponse.OK;
        var status = HttpStatus.OK;

        try{
            service.handle(request);
        }catch (TransacaoSaldoInsuficienteException e){
            status = HttpStatus.UNPROCESSABLE_ENTITY;
            retorno = AutorizacaoResponse.SALDO_INSUFICIENTE;
        }catch (TransacaoSenhaIncorretaException e){
            status = HttpStatus.UNPROCESSABLE_ENTITY;
            retorno = AutorizacaoResponse.SENHA_INVALIDA;
        }catch (CartaoInexistenteException e){
            status = HttpStatus.UNPROCESSABLE_ENTITY;
            retorno = AutorizacaoResponse.CARTAO_INEXISTENTE;
        }
        return ResponseEntity.status(status).body(retorno.toString());
    }
}
