package br.com.sistema.controle.financas.pessoais.config.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class ObterLoginNotFoundException extends RuntimeException {

    public ObterLoginNotFoundException(){
        super(Constantes.erroLoginConta);
    }
}
