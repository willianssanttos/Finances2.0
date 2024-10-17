package br.com.sistema.controle.financas.pessoais.domain.entity.transacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtratoTransacaoEntity {

    private String nomeConta;
    private String tipoConta;
    private String descricao;
    private String categoria;
    private Double valor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataMovimentacao;
    private String tipoTransacao;
    
}
