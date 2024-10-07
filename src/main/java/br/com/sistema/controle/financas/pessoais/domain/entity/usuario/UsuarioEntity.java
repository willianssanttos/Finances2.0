package br.com.sistema.controle.financas.pessoais.domain.entity.usuario;

import br.com.sistema.controle.financas.pessoais.domain.entity.conta.SaldoEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)

public class UsuarioEntity extends SaldoEntity implements Serializable {

    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String numeroCelular;

}
