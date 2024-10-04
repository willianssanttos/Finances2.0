package br.com.sistema.controle.financas.pessoais.domain.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(){
        super(Constantes.ErroCadastroUsuario);
    }
}
