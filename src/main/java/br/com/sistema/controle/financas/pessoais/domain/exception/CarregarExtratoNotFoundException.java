package br.com.sistema.controle.financas.pessoais.domain.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class CarregarExtratoNotFoundException extends RuntimeException{

    public CarregarExtratoNotFoundException(){
        super(Constantes.ErrorRecuperarExtrato);
    }

}
