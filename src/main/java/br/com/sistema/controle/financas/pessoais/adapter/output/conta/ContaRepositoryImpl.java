package br.com.sistema.controle.financas.pessoais.adapter.output.conta;

import br.com.sistema.controle.financas.pessoais.adapter.input.conta.dto.request.ContaRequest;
import br.com.sistema.controle.financas.pessoais.domain.exception.AtualizarContaException;
import br.com.sistema.controle.financas.pessoais.domain.exception.CriarContaException;
import br.com.sistema.controle.financas.pessoais.port.output.conta.IContaRepository;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

        } catch (DataAccessException e){
            logger.error(Constantes.ErroRegistrarNoServidor);
            throw new CriarContaException();
        }
        return conta;
    }

    @Override
    @Transactional
    public ContaRequest editarConta(ContaRequest conta){
        logger.info(Constantes.DebugEditarProcesso);

        try {
            String sql = "SELECT atualizar_conta(?,?,?)";
            jdbcTemplate.execute(sql, (PreparedStatementCallback<Object>) ps ->{
                ps.setInt(1, conta.getIdConta());
                ps.setString(2, conta.getNomeConta());
                ps.setString(3, String.valueOf(conta.getTipoConta()));
                ps.execute();
                return conta;
            });

        } catch (DataAccessException e){
           logger.error(Constantes.ErroEditarRegistroNoServidor);
           throw new AtualizarContaException();
        }
        return conta;
    }
//
//    public void excluirConta(Integer idConta){
//        logger.debug(Constantes.DebugDeletarProcesso + idConta);
//
//        String sql = "SELECT excluir_conta(?)";
//
//        try (Connection conn = DataSourceConfig.getConexao();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, idConta);
//            ps.executeQuery();
//
//            logger.info(Constantes.InfoDeletar + idConta);
//        } catch (SQLException e){
//            logger.error(Constantes.ErroDeletarRegistroNoServidor);
//        }
//    }
}

