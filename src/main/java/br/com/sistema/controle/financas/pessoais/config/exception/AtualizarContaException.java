package br.com.sistema.controle.financas.pessoais.config.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class AtualizarContaException extends RuntimeException{

    public AtualizarContaException(){
        super(Constantes.ErroEditar);
    }
}
