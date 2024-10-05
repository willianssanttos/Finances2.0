package br.com.sistema.controle.financas.pessoais.adapter.input.transacao;

import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.request.TransacaoRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.response.TransacaoResponse;
import br.com.sistema.controle.financas.pessoais.port.input.transacao.ITransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/transacao")
public class TransacaoController implements ITransacaoController{

    @Autowired
    ITransacao iTransacao;

    @PostMapping("/criar-transacao")
    public ResponseEntity<TransacaoResponse> realizarTransacao(@RequestBody TransacaoRequest transacao){
        return new ResponseEntity<>(iTransacao.registrarTransacao(transacao), HttpStatus.CREATED);
    }
}
