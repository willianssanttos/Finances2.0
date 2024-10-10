package br.com.sistema.controle.financas.pessoais.adapter.output.login;

import br.com.sistema.controle.financas.pessoais.adapter.output.mapper.RolesMapper;
import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.RolesEntity;
import br.com.sistema.controle.financas.pessoais.domain.exception.TipoContaNotFoundException;
import br.com.sistema.controle.financas.pessoais.port.output.login.ILogin;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoleRepositoryImpl implements ILogin {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);

    @Override
    @Transactional
    public RolesEntity obterRole(String nomeRole){
        logger.info(Constantes.DebugRegistroProcesso);
        try {
            String sql = "SELECT * FROM buscar_roles(?)";
            return jdbcTemplate.queryForObject(sql, new Object[] { nomeRole }, new RolesMapper());

        } catch (DataAccessException e){
            logger.error(Constantes.ErroBuscarRegistroNoServidor, e.getMessage());
            throw new TipoContaNotFoundException();
        }
    }
}
