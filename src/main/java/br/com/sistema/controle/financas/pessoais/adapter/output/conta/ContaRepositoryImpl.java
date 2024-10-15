package br.com.sistema.controle.financas.pessoais.adapter.output.conta;

import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request.ContaRequest;
import br.com.sistema.controle.financas.pessoais.adapter.output.mapper.ObterContasRowMapper;
import br.com.sistema.controle.financas.pessoais.config.exception.AtualizarContaException;
import br.com.sistema.controle.financas.pessoais.config.exception.CriarContaException;
import br.com.sistema.controle.financas.pessoais.config.exception.DeletarContaException;
import br.com.sistema.controle.financas.pessoais.config.exception.ObterContasNotFoundException;
import br.com.sistema.controle.financas.pessoais.port.output.conta.IContaRepository;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ContaRepositoryImpl implements IContaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public static final Logger logger = LoggerFactory.getLogger(ContaRepositoryImpl.class);

    @Override
    @Transactional
    public ContaEntity criarConta(ContaEntity conta){
        logger.info(Constantes.DebugRegistroProcesso);

        try{
            String sql = "SELECT inserir_conta(?,?,?,?,?,?)";
            Integer IdConta = jdbcTemplate.queryForObject(sql, new Object[]{
                    conta.getIdUsuario(),
                    conta.getIdSaldo(),
                    conta.getTipoConta(),
                    conta.getNomeConta(),
                    conta.getSaldoConta(),
                    conta.getDataDeposito()
            }, Integer.class);
            conta.setIdConta(IdConta);
            logger.info(Constantes.InfoRegistrar);

        } catch (DataAccessException e){
            logger.error(Constantes.ErroRegistrarNoServidor, e.getMessage());
            throw new CriarContaException();
        }
        return conta;
    }

    @Override
    @Transactional
    public List<ContaEntity> obterContasPorUsuario(Integer idUsuario) {
        logger.info(Constantes.DebugBuscarProcesso);

        try {
            String sql = "SELECT * FROM buscar_contas_por_usuario(?)";
            return jdbcTemplate.query(sql, new Object[] { idUsuario }, new ObterContasRowMapper());

        } catch (DataAccessException e) {
            logger.error(Constantes.ErroBuscarRegistroNoServidor, e.getMessage());
            throw new ObterContasNotFoundException();
        }
    }

    @Override
    @Transactional
    public void editarConta(ContaRequest conta) {
        logger.info(Constantes.DebugEditarProcesso);

        try {
            String sql = "SELECT atualizar_conta(?, ?, ?)";
            jdbcTemplate.query(sql, new Object[]{conta.getIdConta(), conta.getNomeConta(), String.valueOf(conta.getTipoConta())}, rs -> {});
            logger.info(Constantes.InfoEditar);

        } catch (DataAccessException e) {
            logger.error(Constantes.ErroEditarRegistroNoServidor, e.getMessage());
            throw new AtualizarContaException();
        }
    }

    @Override
    @Transactional
    public void excluirConta(Integer idConta){
        logger.info(Constantes.DebugDeletarProcesso);

        try {
            String sql = "SELECT excluir_conta(?)";
            jdbcTemplate.update(sql, idConta);
            logger.info(Constantes.InfoDeletar);

        } catch (DataAccessException e){
            logger.error(Constantes.ErroDeletarRegistroNoServidor, e.getMessage());
            throw new DeletarContaException();
        }
    }
}

