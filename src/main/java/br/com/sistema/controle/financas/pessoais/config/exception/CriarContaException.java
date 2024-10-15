package br.com.sistema.controle.financas.pessoais.config.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class CriarContaException extends RuntimeException{
    public CriarContaException(){
        super(Constantes.ErroCadastroConta);
    }
}
