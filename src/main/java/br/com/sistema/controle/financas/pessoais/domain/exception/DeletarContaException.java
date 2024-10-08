package br.com.sistema.controle.financas.pessoais.domain.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class DeletarContaException extends RuntimeException{

    public DeletarContaException(){
        super(Constantes.ErroExcluir);
    }

}
