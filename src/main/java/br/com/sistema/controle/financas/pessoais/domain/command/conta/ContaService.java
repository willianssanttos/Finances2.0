package br.com.sistema.controle.financas.pessoais.domain.command.conta;

import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request.ContaRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ContaResponse;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ContaSimplificadaResponse;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ObterContasUsuarioResponse;
import br.com.sistema.controle.financas.pessoais.domain.Enum.TipoContaEnum;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.TipoContaEntity;
import br.com.sistema.controle.financas.pessoais.config.exception.*;
import br.com.sistema.controle.financas.pessoais.port.input.conta.IConta;
import br.com.sistema.controle.financas.pessoais.port.output.conta.IContaRepository;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.port.output.conta.ISaldoRepository;
import br.com.sistema.controle.financas.pessoais.port.output.conta.ITipoContaRepository;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService implements IConta {

    @Autowired
    private IContaRepository iContaRepository;
    @Autowired
    private ITipoContaRepository iTipoContaRepository;
    @Autowired
    private ISaldoRepository iSaldoRepository;

    private static final Logger logger = LoggerFactory.getLogger(ContaService.class);
    public ContaResponse criarConta(ContaRequest conta){
        logger.info(Constantes.DebugRegistroProcesso);

        TipoContaEntity tipoConta = iTipoContaRepository.obterTiposConta(conta.getTipoConta().name());
        if (tipoConta == null){
            throw new TipoContaNotFoundException();
        }

        try {
            ContaEntity novaConta = ContaEntity.builder()
                    .idUsuario(conta.getIdUsuario())
                    .idSaldo(conta.getIdSaldo())
                    .nomeConta(conta.getNomeConta())
                    .saldoConta(conta.getSaldoConta())
                    .tipoConta(tipoConta.getNomeTipoConta())
                    .dataDeposito(Timestamp.valueOf(LocalDateTime.now()))
                    .build();

            ContaEntity contaCriada = iContaRepository.criarConta(novaConta);
        return mapearConta(tipoConta,contaCriada);

        } catch (Exception e){
            logger.error(Constantes.ErroRegistrarNoServidor, e.getMessage());
           throw new CriarContaException();
        }
    }

    private ContaResponse mapearConta(TipoContaEntity tipoConta, ContaEntity contaCriada){
        return ContaResponse.builder()
                .nomeConta(contaCriada.getNomeConta())
                .tipoConta(TipoContaEnum.valueOf(tipoConta.getNomeTipoConta()))
                .saldoConta(contaCriada.getSaldoConta())
                .dataDeposito(contaCriada.getDataDeposito())
                .build();
    }

    public ObterContasUsuarioResponse obterContasUsuario(String token, Integer idUsario){

        logger.info(Constantes.DebugBuscarProcesso);
        try {
            Double saldo = iSaldoRepository.obterSaldoPorIdUsuario(idUsario);
            List<ContaEntity> contas = iContaRepository.obterContasPorUsuario(idUsario);

            List<ContaSimplificadaResponse> contasSimplificadas = contas.stream()
                    .map(ContaSimplificadaResponse::new)
                    .collect(Collectors.toList());
            return new ObterContasUsuarioResponse(saldo, contasSimplificadas);

        } catch (Exception e){
            logger.error(Constantes.ErrorRecuperarContas, e.getMessage());
            throw new ObterContasNotFoundException();
        }
    }

    public void editarConta(ContaRequest conta){
        logger.info(Constantes.DebugEditarProcesso);
        try {
            iContaRepository.editarConta(conta);
        } catch (Exception e){
            logger.error(Constantes.ErroEditar, e.getMessage());
            throw new AtualizarContaException();
        }
    }

    public void excluirConta(Integer idConta){
        logger.info(Constantes.DebugDeletarProcesso);
        try {
            iContaRepository.excluirConta(idConta);
        } catch (Exception e){
            logger.error(Constantes.ErroExcluir, e.getMessage());
            throw new DeletarContaException();
        }
    }
}
