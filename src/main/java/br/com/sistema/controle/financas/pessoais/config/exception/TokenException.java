package br.com.sistema.controle.financas.pessoais.config.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class TokenException extends RuntimeException{

    public TokenException(){
        super(Constantes.GToken);
    }
}
