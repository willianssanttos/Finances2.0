package br.com.sistema.controle.financas.pessoais.config.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class NomeValidacaoException extends RuntimeException{

    public NomeValidacaoException(){
        super(Constantes.cadastroNome);
    }

}
