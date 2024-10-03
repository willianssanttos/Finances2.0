package br.com.sistema.controle.financas.pessoais.adapter.input.conta;

import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request.ContaRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ContaResponse;
import br.com.sistema.controle.financas.pessoais.port.input.conta.IConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/conta")
public class ContaController implements IContaController {

    @Autowired
    IConta iConta;

    @PostMapping("/criar-conta")
    public ResponseEntity<ContaResponse> criaConta(@RequestBody ContaRequest conta) {
        ContaResponse response = iConta.criarConta(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
