package br.com.sistema.controle.financas.pessoais.domain.exception;

import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class EmailExistenteException extends RuntimeException{
    public EmailExistenteException(){
        super(Constantes.EmailJaCadastrado);
    }
}
