package br.com.sistema.controle.financas.pessoais.adapter.input.conta;

import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request.ContaRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ContaResponse;
import br.com.sistema.controle.financas.pessoais.port.input.conta.IConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/conta")
public class ContaController implements IContaController {

    @Autowired
    IConta iConta;

    @PostMapping("/criar-conta")
    public ResponseEntity<ContaResponse> criaConta(@RequestBody ContaRequest conta) {
        return new ResponseEntity<>(iConta.criarConta(conta), HttpStatus.CREATED);
    }

    @PutMapping("/atualizar-conta/{idConta}")
    public ResponseEntity atualizarConta(@PathVariable Integer idConta, @RequestBody ContaRequest conta){
        return ResponseEntity.status(HttpStatus.OK).body(iConta.editarConta(conta));

    }
}
