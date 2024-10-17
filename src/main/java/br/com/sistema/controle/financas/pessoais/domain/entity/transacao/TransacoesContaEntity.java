package br.com.sistema.controle.financas.pessoais.domain.entity.transacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransacoesContaEntity {

    private Integer idTransacao;
    private Integer idSaldo;
    private Integer idConta;
    private String Descricao;
    private String Categoria;
    private Double Valor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataMovimentacao;
    private int tipo;
}
