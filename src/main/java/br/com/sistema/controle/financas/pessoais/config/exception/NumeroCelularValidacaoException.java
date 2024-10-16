package br.com.sistema.controle.financas.pessoais.config.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class NumeroCelularValidacaoException extends RuntimeException{
    public NumeroCelularValidacaoException(){
        super(Constantes.cadastroCelular);
    }
}
