package br.com.sistema.controle.financas.pessoais.domain.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class EmailValidacaoException extends RuntimeException{
    public EmailValidacaoException(){
        super(Constantes.cadastroEmail);
    }
}
