package br.com.sistema.controle.financas.pessoais.adapter.input.login.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String emailUsuario;
    private String senhaUsuario;
}
