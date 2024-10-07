package br.com.sistema.controle.financas.pessoais.domain.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class NumeroCelularValidacaoException extends RuntimeException{
    public NumeroCelularValidacaoException(){
        super(Constantes.cadastroCelular);
    }
}
