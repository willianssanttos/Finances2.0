package br.com.sistema.controle.financas.pessoais.domain.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(){
        super(Constantes.EmailJaCadastrado);
    }
}
