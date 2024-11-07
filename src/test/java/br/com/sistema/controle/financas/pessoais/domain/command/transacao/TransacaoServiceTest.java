package br.com.sistema.controle.financas.pessoais.domain.command.transacao;

import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.request.TransacaoRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.response.TransacaoResponse;
import br.com.sistema.controle.financas.pessoais.config.exception.CarregarExtratoNotFoundException;
import br.com.sistema.controle.financas.pessoais.domain.Enum.CategoriaEnum;
import br.com.sistema.controle.financas.pessoais.domain.entity.transacao.ExtratoTransacaoEntity;
import br.com.sistema.controle.financas.pessoais.domain.entity.transacao.TransacoesContaEntity;
import br.com.sistema.controle.financas.pessoais.port.output.transacao.ITransacaoContaRepository;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private ITransacaoContaRepository iTransacaoContaRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private TransacaoService transacaoService;

    private TransacaoRequest transacaoRequest;

    private TransacoesContaEntity transacoesContaEntity;

    private List<ExtratoTransacaoEntity> extratoTransacaoList;

    @BeforeEach
    void setUp(){
        transacaoRequest = new TransacaoRequest();
        transacaoRequest.setIdSaldo(1);
        transacaoRequest.setIdConta(1);
        transacaoRequest.setDescricao("Conta de luz");
        transacaoRequest.setCategoria("CASA");
        transacaoRequest.setValor(250.00);
        transacaoRequest.setTipo(2);
        transacaoRequest.setDataMovimentacao(Timestamp.valueOf(LocalDateTime.now()));

        transacoesContaEntity = TransacoesContaEntity.builder()
                .idSaldo(1)
                .idConta(1)
                .Descricao("Conta de luz")
                .Categoria(String.valueOf(CategoriaEnum.CASA))
                .Valor(250.00)
                .tipo(2)
                .dataMovimentacao(LocalDateTime.now())
                .build();

        ExtratoTransacaoEntity extrato1 = new ExtratoTransacaoEntity("Bradesco", "CORRENTE", "Conta de luz", "CASA", 250.00, LocalDateTime.now(), "2");
        ExtratoTransacaoEntity extrato2 = new ExtratoTransacaoEntity("Bradesco", "CORRENTE", "Conta de luz", "CASA", 250.00,LocalDateTime.now(), "2");
        extratoTransacaoList = Arrays.asList(extrato1,extrato2);
    }
    @Test
    void registrarTransacao() {
        when(iTransacaoContaRepository.inserirTransacao(any(TransacoesContaEntity.class))).thenReturn(transacoesContaEntity);

        TransacaoResponse response = transacaoService.registrarTransacao(transacaoRequest);

        assertNotNull(response);
        assertNotNull("Conta de luz", response.getDescricao());
        assertNotNull(String.valueOf(CategoriaEnum.valueOf(response.getCategoria())));
        assertNotNull(250.00, String.valueOf(response.getValor()));
        assertNotNull(2, String.valueOf(response.getTipo()));
        assertNotNull(LocalDateTime.now(), String.valueOf(response.getDataMovimentacao()));

        verify(iTransacaoContaRepository, times(1)).inserirTransacao(any(TransacoesContaEntity.class));
    }

    @Test
    void obterExtratoPorMes_deveRetornarListaDeTransacoes_quandoDadosValidos() {
        Integer idUsurio = 1;
        int mes = 10;
        int ano = 2023;

        when(iTransacaoContaRepository.obterExtratoPorMes(idUsurio,mes,ano)).thenReturn(extratoTransacaoList);

        List<ExtratoTransacaoEntity> resultado = transacaoService.obterExtratoPorMes(idUsurio,mes,ano);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Conta de luz", resultado.get(0).getDescricao());

        verify(iTransacaoContaRepository, times(1)).obterExtratoPorMes(idUsurio,mes,ano);
        verify(logger, times(1)).info(Constantes.DebugBuscarProcesso);
    }

    @Test
    void obterExtratoPorMes_deveRetornarExtratoNotFoundException(){
        Integer idUsario = 1;
        int mes = 10;
        int ano = 2023;

        when(iTransacaoContaRepository.obterExtratoPorMes(idUsario,mes,ano)).thenThrow(new RuntimeException());

        assertThrows(CarregarExtratoNotFoundException.class, () -> transacaoService.obterExtratoPorMes(idUsario,mes,ano));

        verify(iTransacaoContaRepository, times(1)).obterExtratoPorMes(idUsario,mes,ano);
        verify(logger, times(1)).info(Constantes.DebugBuscarProcesso);
        verify(logger, times(1)).info(Constantes.ErroBuscarRegistroNoServidor);
    }
}