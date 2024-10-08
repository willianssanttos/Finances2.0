package br.com.sistema.controle.financas.pessoais.domain.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class CriarTransacaoException extends RuntimeException{

    public CriarTransacaoException(){
        super(Constantes.ErroCadastroTransacao);
    }
}
