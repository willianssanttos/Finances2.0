package br.com.sistema.controle.financas.pessoais.adapter.output.transacao;

import br.com.sistema.controle.financas.pessoais.adapter.output.mapper.ExtratoTransacaoRowMapper;
import br.com.sistema.controle.financas.pessoais.domain.entity.transacao.ExtratoTransacaoEntity;
import br.com.sistema.controle.financas.pessoais.domain.exception.CarregarExtratoNotFoundException;
import br.com.sistema.controle.financas.pessoais.domain.exception.CriarTransacaoException;
import br.com.sistema.controle.financas.pessoais.port.output.transacao.ITransacaoContaRepository;
import br.com.sistema.controle.financas.pessoais.domain.entity.transacao.TransacoesContaEntity;
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
public class TransacaoContaRepositoryImpl implements ITransacaoContaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(TransacaoContaRepositoryImpl.class);
    @Override
    @Transactional
    public TransacoesContaEntity inserirTransacao(TransacoesContaEntity transacao){
        logger.info(Constantes.DebugRegistroProcesso);

        try {
            String sql = "SELECT inserir_transacao(?,?,?,?,?,?,?)";
            Integer idTransacao = jdbcTemplate.queryForObject(sql, new Object[] {
                    transacao.getIdSaldo(),
                    transacao.getIdConta(),
                    transacao.getDescricao(),
                    transacao.getCategoria(),
                    transacao.getValor(),
                    transacao.getDataMovimentacao(),
                    transacao.getTipo()
            }, Integer.class);
            transacao.setIdTransacao(idTransacao);
            logger.info(Constantes.InfoRegistrar);

        } catch (DataAccessException e){
            logger.error(Constantes.ErroRegistrarNoServidor, e.getMessage());
            throw new CriarTransacaoException();
        }
        return transacao;
    }
    @Override
    @Transactional
    public List<ExtratoTransacaoEntity> obterExtratoPorMes(Integer idUsuario, int mes, int ano){
        logger.info(Constantes.DebugBuscarProcesso);

        try {
            String sql = "SELECT * FROM buscar_extrato_por_usuario(?,?,?)";
            return jdbcTemplate.query(sql, new Object[]{idUsuario, mes, ano }, new ExtratoTransacaoRowMapper());

        } catch (DataAccessException e){
            logger.error(Constantes.ErroBuscarRegistroNoServidor);
            throw new CarregarExtratoNotFoundException();
        }
    }
}
