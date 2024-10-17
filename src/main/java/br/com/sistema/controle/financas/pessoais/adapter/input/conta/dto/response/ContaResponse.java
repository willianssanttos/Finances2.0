package br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response;

import br.com.sistema.controle.financas.pessoais.domain.Enum.TipoContaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ContaResponse {

    private String nomeConta;
    private Double saldoConta;
    private TipoContaEnum tipoConta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataDeposito;

}
