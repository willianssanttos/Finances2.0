package br.com.sistema.controle.financas.pessoais.domain.command.usuario;

import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.request.UsuarioRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response.UsuarioResponse;
import br.com.sistema.controle.financas.pessoais.domain.Enum.RolesEnum;
import br.com.sistema.controle.financas.pessoais.config.security.config.SecurityConfiguration;
import br.com.sistema.controle.financas.pessoais.config.exception.*;
import br.com.sistema.controle.financas.pessoais.port.input.usuario.IUsuario;
import br.com.sistema.controle.financas.pessoais.port.output.conta.ISaldoRepository;
import br.com.sistema.controle.financas.pessoais.port.output.login.ILoginRepository;
import br.com.sistema.controle.financas.pessoais.port.output.usuario.IUsuarioRepository;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.SaldoEntity;
import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarEmail;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNome;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNumeroCelular;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarSenha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UsuarioService implements IUsuario {
    @Autowired
    private ILoginRepository iLoginRepository;
    @Autowired
    private IUsuarioRepository iUsuarioRepository;
    @Autowired
    private ISaldoRepository iSaldoRepository;
    @Autowired
    private SecurityConfiguration securityConfiguration;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public UsuarioResponse criarUsuario(UsuarioRequest usuario) {
        logger.info(Constantes.DebugRegistroProcesso);

        validarDados(usuario);

        try {
            UsuarioEntity novoUsuario = UsuarioEntity.builder()
                    .role(String.valueOf(RolesEnum.ROLE_CLIENTE))
                    .nomeUsuario(usuario.getNomeUsuario())
                    .emailUsuario(usuario.getEmailUsuario())
                    .senhaUsuario(securityConfiguration.passwordEncoder().encode(usuario.getSenhaUsuario()))
                    .numeroCelular(ValidarNumeroCelular.formatarNumeroCelular(usuario.getNumeroCelular()))
                    .build();


            UsuarioEntity usuarioCriado = iUsuarioRepository.criarUsuario(novoUsuario);

            SaldoEntity inserirSaldo = SaldoEntity.builder()
                    .idUsuario(usuarioCriado.getIdUsuario())
                    .saldoAtual(0.00)
                    .dataAtualizadaSaldo(Timestamp.valueOf(LocalDateTime.now()))
                    .build();

            SaldoEntity saldoCriado = iSaldoRepository.inserirSaldo(inserirSaldo);

            return mapearUsuario(usuarioCriado);
        } catch (Exception e){
            logger.error(Constantes.ErroRegistrarNoServidor);
            throw new CriarUsuarioException();
        }
    }
    private UsuarioResponse mapearUsuario(UsuarioEntity usuarioCriado){
        return UsuarioResponse.builder()
                .nomeUsuario(usuarioCriado.getNomeUsuario())
                .emailUsuario(usuarioCriado.getEmailUsuario())
                .numeroCelular(usuarioCriado.getNumeroCelular())
                .build();
    }

    private void validarDados(UsuarioRequest usuario){

        if (!ValidarNome.validarNome(usuario.getNomeUsuario())){
            throw new NomeValidacaoException();
        }

        if(!ValidarEmail.validaEmail(usuario.getEmailUsuario())){
            throw new EmailValidacaoException();
        }

        Boolean emailExiste = iUsuarioRepository.verificarEmailExistente(usuario.getEmailUsuario());
        if (emailExiste != null && emailExiste){
            throw new EmailExistenteException();
        }

        if (!ValidarSenha.validarSenha(usuario.getSenhaUsuario())){
            throw new SenhaValidacaoException();
        }

        if (!ValidarNumeroCelular.validarNumeroCelular(usuario.getNumeroCelular())) {
            throw new NumeroCelularValidacaoException();
        }
    }

}
