package br.com.sistema.controle.financas.pessoais.domain.entity.usuario;

import br.com.sistema.controle.financas.pessoais.domain.entity.conta.SaldoEntity;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UsuarioEntity extends SaldoEntity implements Serializable {

    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String numeroCelular;

//    public String getNomeUsuario() {
//        return nomeUsuario;
//    }
//
//    public void setNomeUsuario(String nomeUsuario) {
//        this.nomeUsuario = nomeUsuario;
//    }
//
//    public String getEmailUsuario() {
//        return emailUsuario;
//    }
//
//    public void setEmailUsuario(String emailUsuario) {
//        this.emailUsuario = emailUsuario;
//    }
//
//    public String getSenhaUsuario() {
//        return senhaUsuario;
//    }
//
//    public void setSenhaUsuario(String senhaUsuario) {
//        this.senhaUsuario = senhaUsuario;
//    }
//
//    public String getNumeroCelular() {
//        return numeroCelular;
//    }
//
//    public void setNumeroCelular(String numeroCelular) {
//        this.numeroCelular = numeroCelular;
//    }
}
