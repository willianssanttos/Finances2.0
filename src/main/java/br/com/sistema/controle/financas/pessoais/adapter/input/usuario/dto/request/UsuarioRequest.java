package br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UsuarioRequest {

    private Integer idUsuario;
    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String numeroCelular;
}
