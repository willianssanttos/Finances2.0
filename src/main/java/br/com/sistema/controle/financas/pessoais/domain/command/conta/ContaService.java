package br.com.sistema.controle.financas.pessoais.domain.command.conta;

import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request.ContaRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.response.ContaResponse;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.TipoContaEntity;
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
    private IContaRepository IContaRepository;

    @Autowired
    private ITipoContaRepository iTipoContaRepository;

//    @Autowired
//    private ISaldoRepository ISaldoRepository;

    private static final Logger logger = LoggerFactory.getLogger(ContaService.class);


    public ContaResponse criarConta(ContaRequest conta){

        logger.debug("idUsuario" + conta.getIdUsuario(), "idSaldo" + conta.getIdSaldo());

        TipoContaEntity tipoConta = iTipoContaRepository.obterTiposConta(conta.getTipoConta().name());
        if (tipoConta != null){
            throw new IllegalArgumentException(Constantes.cadastroTipoConta);
        }
        try {
        ContaEntity novaConta = new ContaEntity();
        novaConta.setIdUsuario(conta.getIdUsuario());
        novaConta.setIdSaldo(conta.getIdSaldo());
        novaConta.setTipoConta(String.valueOf(conta.getTipoConta()));
        novaConta.setNomeConta(conta.getNomeConta());
        novaConta.setSaldoConta(conta.getSaldoConta());
        novaConta.setDataDeposito(Timestamp.valueOf(LocalDateTime.now()));

        ContaEntity contaCriada = IContaRepository.criarConta(novaConta);
        } catch (Exception e){
            logger.error(Constantes.ErroCadastroUsuario, e);
        }
        return new ContaResponse();
    }
//    public ContaEntity criarConta(ContaEntity conta){
//        try {
//            return IContaRepository.criarConta(conta);
//        } catch (Exception e){
//            logger.error(Constantes.ErroCadastroConta);
//        }
//        return conta;
//    }

//    public List<String> obterTodosTiposConta() {
//        try {
//            return ITipoContaDao.obterTiposConta();
//        } catch (Exception e){
//            logger.error(Constantes.cadastroTipoConta);
//        }
//        return obterTodosTiposConta();
//    }
//
//    public void editarConta(ContaEntity conta){
//        try {
//            IContaDao.editarConta(conta);
//        } catch (Exception e){
//            logger.error(Constantes.ErroEditar);
//        }
//    }
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
