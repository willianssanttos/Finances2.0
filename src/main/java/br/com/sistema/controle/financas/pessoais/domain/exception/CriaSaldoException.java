package br.com.sistema.controle.financas.pessoais.domain.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class CriaSaldoException extends RuntimeException{

    public CriaSaldoException(){
        super(Constantes.ErrorRecuperarSaldo);
    }

}
