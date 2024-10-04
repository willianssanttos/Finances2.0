package br.com.sistema.controle.financas.pessoais.domain.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class ContaNotFoundException extends RuntimeException{
    public ContaNotFoundException(){
        super(Constantes.ErroCadastroConta);
    }
}
