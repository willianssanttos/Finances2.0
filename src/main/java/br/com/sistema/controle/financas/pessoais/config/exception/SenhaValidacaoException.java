package br.com.sistema.controle.financas.pessoais.config.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class SenhaValidacaoException extends RuntimeException{

    public SenhaValidacaoException(){
        super(Constantes.cadastroSenha);
    }

}
