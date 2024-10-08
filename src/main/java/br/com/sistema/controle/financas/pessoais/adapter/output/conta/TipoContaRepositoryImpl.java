package br.com.sistema.controle.financas.pessoais.adapter.output.conta;

import br.com.sistema.controle.financas.pessoais.adapter.output.mapper.TipoContaRowMapper;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.TipoContaEntity;
import br.com.sistema.controle.financas.pessoais.domain.exception.TipoContaNotFoundException;
import br.com.sistema.controle.financas.pessoais.port.output.conta.ITipoContaRepository;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TipoContaRepositoryImpl implements ITipoContaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(TipoContaRepositoryImpl.class);

    @Override
    @Transactional
    public TipoContaEntity obterTiposConta(String nomeTipoConta){
        logger.info(Constantes.DebugRegistroProcesso);
        try {
            String sql = "SELECT * FROM buscar_tipo_conta_por_nome(?)";
            return jdbcTemplate.queryForObject(sql, new Object[] { nomeTipoConta }, new TipoContaRowMapper());

        } catch (DataAccessException e){
            logger.error(Constantes.ErroBuscarRegistroNoServidor, e.getMessage());
           throw new TipoContaNotFoundException();
        }
    }
}
