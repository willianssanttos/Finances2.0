package br.com.sistema.controle.financas.pessoais.domain.command.usuario;

import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.request.UsuarioRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response.UsuarioResponse;
import br.com.sistema.controle.financas.pessoais.domain.exception.EmailExistenteException;
import br.com.sistema.controle.financas.pessoais.domain.exception.EmailValidacaoException;
import br.com.sistema.controle.financas.pessoais.domain.exception.NumeroCelularValidacaoException;
import br.com.sistema.controle.financas.pessoais.domain.exception.CriarUsuarioException;
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
        logger.info(Constantes.DebugRegistroProcesso);

        if(!ValidarEmail.validaEmail(usuario.getEmailUsuario())){
            throw new EmailValidacaoException();
        }

        Boolean emailExiste = IUsuarioRepository.verificarEmailExistente(usuario.getEmailUsuario());
        if (emailExiste != null && emailExiste){
            throw new EmailExistenteException();
        }

        if (!ValidarNumeroCelular.validarNumeroCelular(usuario.getNumeroCelular())) {
            throw new NumeroCelularValidacaoException();
        }

        try {
            UsuarioEntity novoUsuario = UsuarioEntity.builder()
                    .nomeUsuario(usuario.getNomeUsuario())
                    .emailUsuario(usuario.getEmailUsuario())
                    .senhaUsuario(usuario.getSenhaUsuario())
                    .numeroCelular(ValidarNumeroCelular.formatarNumeroCelular(usuario.getNumeroCelular()))
                    .build();

            UsuarioEntity usuarioCriado =  IUsuarioRepository.criarUsuario(novoUsuario);

            SaldoEntity inserirSaldo = SaldoEntity.builder()
                    .idUsuario(usuarioCriado.getIdUsuario())
                    .saldoAtual(0.00)
                    .dataAtualizadaSaldo(Timestamp.valueOf(LocalDateTime.now()))
                    .build();

            SaldoEntity saldoCriado = ISaldoRepository.inserirSaldo(inserirSaldo);

            return mapearUsuario(usuarioCriado);
        } catch (Exception e){
            logger.error(Constantes.ErroRegistrarNoServidor);
            throw new CriarUsuarioException();
        }
    }
    public UsuarioResponse mapearUsuario(UsuarioEntity usuarioCriado){
        return UsuarioResponse.builder()
                .nomeUsuario(usuarioCriado.getNomeUsuario())
                .emailUsuario(usuarioCriado.getEmailUsuario())
                .senhaUsuario(usuarioCriado.getSenhaUsuario())
                .numeroCelular(usuarioCriado.getNumeroCelular())
                .build();
    }


}
