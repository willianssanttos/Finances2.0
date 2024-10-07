package br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.request;

import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request.SaldoRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UsuarioRequest extends SaldoRequest {

    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String numeroCelular;
}
