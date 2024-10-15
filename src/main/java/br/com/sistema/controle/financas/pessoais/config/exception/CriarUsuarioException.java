package br.com.sistema.controle.financas.pessoais.config.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class CriarUsuarioException extends RuntimeException{
    public CriarUsuarioException(){
        super(Constantes.ErroCadastroUsuario);
    }
}
