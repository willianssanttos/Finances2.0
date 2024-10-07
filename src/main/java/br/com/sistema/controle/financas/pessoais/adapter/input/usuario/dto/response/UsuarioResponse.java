package br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String numeroCelular;
}
