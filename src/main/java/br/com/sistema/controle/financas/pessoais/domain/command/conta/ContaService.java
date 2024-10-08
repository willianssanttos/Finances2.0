package br.com.sistema.controle.financas.pessoais.domain.command.conta;

import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request.ContaRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ContaResponse;
import br.com.sistema.controle.financas.pessoais.domain.command.Enum.TipoContaEnum;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.TipoContaEntity;
import br.com.sistema.controle.financas.pessoais.domain.exception.AtualizarContaException;
import br.com.sistema.controle.financas.pessoais.domain.exception.CriarContaException;
import br.com.sistema.controle.financas.pessoais.domain.exception.TipoContaNotFoundException;
import br.com.sistema.controle.financas.pessoais.port.input.conta.IConta;
import br.com.sistema.controle.financas.pessoais.port.output.conta.IContaRepository;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.port.output.conta.ITipoContaRepository;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ContaService implements IConta {

    @Autowired
    private IContaRepository iContaRepository;
    @Autowired
    private ITipoContaRepository iTipoContaRepository;

//    @Autowired
//    private ISaldoRepository ISaldoRepository;

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

    public void editarConta(ContaRequest conta){
        try {
            iContaRepository.editarConta(conta);
        } catch (Exception e){
            logger.error(Constantes.ErroEditar, e.getMessage());
            throw new AtualizarContaException();
        }
    }
//
//    public void excluirConta(Integer idConta){
//        try {
//            IContaDao.excluirConta(idConta);
//        } catch (Exception e){
//            logger.error(Constantes.ErroExcluir);
//        }
//    }
//
//    public Double obterSaldo(Integer idUsuario){
//        try {
//            return ISaldoDao.obterSaldoPorIdUsuario(idUsuario);
//        } catch (Exception e){
//            logger.error(Constantes.ErrorRecuperarSaldo);
//        }
//        return obterSaldo(idUsuario);
//    }
//
//    public List<ContaEntity> obterContasPorIdUsuario(Integer idUsuario){
//        try {
//            return IContaDao.obterContasPorUsuario(idUsuario);
//        } catch (Exception e){
//           logger.error(Constantes.ErrorRecuperarContas);
//        }
//        return obterContasPorIdUsuario(idUsuario);
//    }
}
