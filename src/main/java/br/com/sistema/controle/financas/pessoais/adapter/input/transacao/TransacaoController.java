package br.com.sistema.controle.financas.pessoais.adapter.input.transacao;

import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.response.ExtratoTransacaoResponse;
import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.request.TransacaoRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.response.TransacaoResponse;
import br.com.sistema.controle.financas.pessoais.domain.entity.transacao.ExtratoTransacaoEntity;
import br.com.sistema.controle.financas.pessoais.port.input.transacao.ITransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/transacao")
public class TransacaoController implements ITransacaoController{

    @Autowired
    ITransacao iTransacao;

    @PostMapping("/criar-transacao")
    public ResponseEntity<TransacaoResponse> realizarTransacao(@RequestBody TransacaoRequest transacao){
        return new ResponseEntity<>(iTransacao.registrarTransacao(transacao), HttpStatus.CREATED);
    }

    @GetMapping("/extrato-transacao/{idUsuario}")
    public ResponseEntity<ExtratoTransacaoResponse> extratoTransacao(
            @PathVariable Integer idUsuario,
            @RequestParam Integer mes,
            @RequestParam Integer ano){

        List<ExtratoTransacaoEntity> extrato = iTransacao.obterExtratoPorMes(idUsuario, mes, ano);
        ExtratoTransacaoResponse response = ExtratoTransacaoResponse.converterExtrato(extrato);
        return ResponseEntity.ok(response);
    }

}
