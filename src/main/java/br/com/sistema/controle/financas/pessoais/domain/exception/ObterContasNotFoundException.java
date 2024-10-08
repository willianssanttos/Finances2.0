package br.com.sistema.controle.financas.pessoais.domain.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class ObterContasNotFoundException extends RuntimeException{
    public ObterContasNotFoundException(){
        super(Constantes.ErrorRecuperarContas);
    }

}
