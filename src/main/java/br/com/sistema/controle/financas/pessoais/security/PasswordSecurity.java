package br.com.sistema.controle.financas.pessoais.security;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordSecurity {

    public static String encriptarSenha(String senha) {

        //senha que sera encriptada
        var hashSenha = BCrypt.hashpw(senha, BCrypt.gensalt());
        return hashSenha;
    }

    public static boolean checkSenha(String senha, String senhaHash) {
        //forma de verificar a senha
        return BCrypt.checkpw(senha, senhaHash);
    }
}
