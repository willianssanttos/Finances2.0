package br.com.sistema.controle.financas.pessoais.adapter.output.conta;

import br.com.sistema.controle.financas.pessoais.domain.entity.conta.TipoContaEntity;
import br.com.sistema.controle.financas.pessoais.port.output.conta.ITipoContaRepository;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TipoContaRepositoryImpl implements ITipoContaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(TipoContaRepositoryImpl.class);

    public TipoContaEntity obterTiposConta(String nomeTipoConta){
        String sql = "SELECT * FROM buscar_tipo_conta_por_nome(?)";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TipoContaEntity.class), nomeTipoConta);

    }
//    public TipoContaEntity obterTiposConta(String nomeTipoConta) {
//
//        String sql = "SELECT * FROM buscar_tipo_conta_por_nome(?)";
//        try {
//            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TipoContaEntity.class), nomeTipoConta);
//
//        } catch (Exception e) {
//            logger.error(Constantes.ErroBuscarRegistroNoServidor);
//        }
//        return nomeTipoConta;
//    }
}
