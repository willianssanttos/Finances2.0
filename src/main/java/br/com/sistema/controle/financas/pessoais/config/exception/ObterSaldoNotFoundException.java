package br.com.sistema.controle.financas.pessoais.config.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class ObterSaldoNotFoundException extends RuntimeException{

    public ObterSaldoNotFoundException(){
        super(Constantes.ErrorRecuperarSaldo);
    }

}
