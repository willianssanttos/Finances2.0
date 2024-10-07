package br.com.sistema.controle.financas.pessoais.domain.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class SenhaValidacaoException extends RuntimeException{

    public SenhaValidacaoException(){
        super(Constantes.cadastroSenha);
    }

}
