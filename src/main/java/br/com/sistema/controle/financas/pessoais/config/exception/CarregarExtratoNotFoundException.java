package br.com.sistema.controle.financas.pessoais.config.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class CarregarExtratoNotFoundException extends RuntimeException{

    public CarregarExtratoNotFoundException(){
        super(Constantes.ErrorRecuperarExtrato);
    }

}
