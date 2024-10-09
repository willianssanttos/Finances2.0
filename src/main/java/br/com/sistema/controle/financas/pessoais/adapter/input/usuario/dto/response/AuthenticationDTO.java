package br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {

    private String email;
    private String senha;
}
