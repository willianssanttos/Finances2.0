package br.com.sistema.controle.financas.pessoais.config.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(){
        super(Constantes.ErroRole);
    }
}
