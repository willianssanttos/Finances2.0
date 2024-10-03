package br.com.sistema.controle.financas.pessoais.domain.command.usuario;

import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.request.UsuarioRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response.UsuarioResponse;
import br.com.sistema.controle.financas.pessoais.port.input.usuario.IUsuario;
import br.com.sistema.controle.financas.pessoais.port.output.conta.ISaldoRepository;
import br.com.sistema.controle.financas.pessoais.port.output.usuario.IUsuarioRepository;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.SaldoEntity;
import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.security.PasswordSecurity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarEmail;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNumeroCelular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UsuarioService implements IUsuario {

    @Autowired
    private IUsuarioRepository IUsuarioRepository;
    @Autowired
    private ISaldoRepository ISaldoRepository;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);


    public UsuarioResponse criarUsuario(UsuarioRequest usuario) {

        if(!ValidarEmail.validaEmail(usuario.getEmailUsuario())){
            throw new IllegalArgumentException(Constantes.cadastroEmail);
        }

        Boolean emailExiste = IUsuarioRepository.verificarEmailExistente(usuario.getEmailUsuario());
        if (emailExiste != null && emailExiste){
            throw new IllegalArgumentException(Constantes.EmailJaCadastrado);
        }

        if (!ValidarNumeroCelular.validarNumeroCelular(usuario.getNumeroCelular())) {
            throw new IllegalArgumentException(Constantes.cadastroCelular);
        }


        try {
            UsuarioEntity novoUsuario = new UsuarioEntity();
            novoUsuario.setNomeUsuario(usuario.getNomeUsuario());
            novoUsuario.setEmailUsuario(usuario.getEmailUsuario());
            novoUsuario.setSenhaUsuario(PasswordSecurity.encriptarSenha(usuario.getSenhaUsuario()));
            novoUsuario.setNumeroCelular(ValidarNumeroCelular.formatarNumeroCelular(usuario.getNumeroCelular()));
            UsuarioEntity usuarioCriado =  IUsuarioRepository.criarUsuario(novoUsuario);

            if (usuarioCriado.getIdUsuario() != null) {
                SaldoEntity inserirSaldo = new SaldoEntity();
                inserirSaldo.setIdUsuario(novoUsuario.getIdUsuario());
                inserirSaldo.setSaldoAtual(0.00);
                inserirSaldo.setDataAtualizadaSaldo(Timestamp.valueOf(LocalDateTime.now()));

                ISaldoRepository.inserirSaldo(inserirSaldo);
            }
            return new UsuarioResponse(usuarioCriado.getIdUsuario(), usuarioCriado.getNomeUsuario(), usuarioCriado.getEmailUsuario(), usuarioCriado.getSenhaUsuario(), usuarioCriado.getNumeroCelular());
        } catch (Exception e){
            logger.error(Constantes.ErroCadastroUsuario, e);
        }
        return new UsuarioResponse();
    }


}
