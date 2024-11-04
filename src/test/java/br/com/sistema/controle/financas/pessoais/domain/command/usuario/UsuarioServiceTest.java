package br.com.sistema.controle.financas.pessoais.domain.command.usuario;

import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.request.UsuarioRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response.UsuarioResponse;
import br.com.sistema.controle.financas.pessoais.config.exception.*;
import br.com.sistema.controle.financas.pessoais.config.security.config.SecurityConfiguration;
import br.com.sistema.controle.financas.pessoais.domain.Enum.RolesEnum;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.SaldoEntity;
import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.port.output.conta.ISaldoRepository;
import br.com.sistema.controle.financas.pessoais.port.output.login.ILoginRepository;
import br.com.sistema.controle.financas.pessoais.port.output.usuario.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private ILoginRepository iLoginRepository;

    @Mock
    private IUsuarioRepository iUsuarioRepository;

    @Mock
    private ISaldoRepository iSaldoRepository;

    @Mock
    private SecurityConfiguration securityConfiguration;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioRequest usuarioRequest;
    private UsuarioEntity usuarioEntity;
    private SaldoEntity saldoEntity;

    @BeforeEach
    void setUp() {
        usuarioRequest = new UsuarioRequest();
        usuarioRequest.setNomeUsuario("John Doe");
        usuarioRequest.setEmailUsuario("john.doe@example.com");
        usuarioRequest.setSenhaUsuario("@Password123");
        usuarioRequest.setNumeroCelular("11999999999");

        usuarioEntity = UsuarioEntity.builder()
                .idUsuario(1)
                .nomeUsuario(usuarioRequest.getNomeUsuario())
                .emailUsuario(usuarioRequest.getEmailUsuario())
                .senhaUsuario("encodedPassword")
                .numeroCelular(usuarioRequest.getNumeroCelular())
                .role(String.valueOf(RolesEnum.ROLE_CLIENTE))
                .build();

        saldoEntity = SaldoEntity.builder()
                .idUsuario(usuarioEntity.getIdUsuario())
                .saldoAtual(0.00)
                .build();
    }

    @Test
    void criarUsuario_deveCriarUsuarioComSucesso() {
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        when(securityConfiguration.passwordEncoder()).thenReturn(passwordEncoder);
        when(passwordEncoder.encode(usuarioRequest.getSenhaUsuario())).thenReturn("encodedPassword");
        when(iUsuarioRepository.verificarEmailExistente(usuarioRequest.getEmailUsuario())).thenReturn(false);
        when(iUsuarioRepository.criarUsuario(any(UsuarioEntity.class))).thenReturn(usuarioEntity);
        when(iSaldoRepository.inserirSaldo(any(SaldoEntity.class))).thenReturn(saldoEntity);

        UsuarioResponse response = usuarioService.criarUsuario(usuarioRequest);

        assertNotNull(response);
        assertEquals("John Doe", response.getNomeUsuario());
        assertEquals("john.doe@example.com", response.getEmailUsuario());
        assertEquals("11999999999", response.getNumeroCelular());

        verify(iUsuarioRepository, times(1)).criarUsuario(any(UsuarioEntity.class));
        verify(iSaldoRepository, times(1)).inserirSaldo(any(SaldoEntity.class));
    }

    @Test
    void criarUsuario_deveLancarEmailExistenteException_quandoEmailJaExiste() {
        when(iUsuarioRepository.verificarEmailExistente(usuarioRequest.getEmailUsuario())).thenReturn(true);
        assertThrows(EmailExistenteException.class, () -> usuarioService.criarUsuario(usuarioRequest));
    }

    @Test
    void criarUsuario_deveLancarNomeValidacaoException_quandoNomeInvalido() {
        usuarioRequest.setNomeUsuario("Invalid Name@!");
        assertThrows(NomeValidacaoException.class, () -> usuarioService.criarUsuario(usuarioRequest));
    }

    @Test
    void criarUsuario_deveLancarEmailValidacaoException_quandoEmailInvalido() {
        usuarioRequest.setEmailUsuario("invalid-email");
        assertThrows(EmailValidacaoException.class, () -> usuarioService.criarUsuario(usuarioRequest));
    }

    @Test
    void criarUsuario_deveLancarSenhaValidacaoException_quandoSenhaInvalida() {
        usuarioRequest.setSenhaUsuario("123");
        assertThrows(SenhaValidacaoException.class, () -> usuarioService.criarUsuario(usuarioRequest));
    }

    @Test
    void criarUsuario_deveLancarNumeroCelularValidacaoException_quandoNumeroCelularInvalido() {
        usuarioRequest.setNumeroCelular("12345");
        assertThrows(NumeroCelularValidacaoException.class, () -> usuarioService.criarUsuario(usuarioRequest));
    }
}