package br.com.sistema.controle.financas.pessoais.adapter.input.conta;

import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request.ContaRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ContaResponse;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ObterContasUsuarioResponse;
import br.com.sistema.controle.financas.pessoais.port.input.conta.IConta;
import br.com.sistema.controle.financas.pessoais.port.output.conta.ISaldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/conta")
public class ContaController implements IContaController {

    @Autowired
    IConta iConta;

    @Autowired
    ISaldoRepository iSaldoRepository;

    @PostMapping("/criar-conta")
    public ResponseEntity<ContaResponse> criaConta(@RequestBody ContaRequest conta) {
        return new ResponseEntity<>(iConta.criarConta(conta), HttpStatus.CREATED);
    }

    @PutMapping("/atualizar-conta/{idConta}")
    public ResponseEntity<Void> atualizarConta(@PathVariable Integer idConta, @RequestBody ContaRequest conta) {
        conta.setIdConta(idConta);
        iConta.editarConta(conta);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("obter-contas/{idUsuario}")
    public ResponseEntity<ObterContasUsuarioResponse> obterContas(@PathVariable Integer idUsuario){
        ObterContasUsuarioResponse response = iConta.obterContasUsuario(idUsuario);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deletar-conta/{idConta}")
    public ResponseEntity<Void> deletarConta(@PathVariable Integer idConta){
        iConta.excluirConta(idConta);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
