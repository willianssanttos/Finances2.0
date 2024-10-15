package br.com.sistema.controle.financas.pessoais.config.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class TipoContaNotFoundException extends RuntimeException{
    public TipoContaNotFoundException(){
        super(Constantes.cadastroTipoConta);
    }
}
