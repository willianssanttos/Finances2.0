package br.com.sistema.controle.financas.pessoais.adapter.output.login;

import br.com.sistema.controle.financas.pessoais.adapter.output.mapper.LoginRowMapper;
import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.config.exception.ObterLoginNotFoundException;
import br.com.sistema.controle.financas.pessoais.port.output.login.ILoginRepository;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LoginRepositoryImpl implements ILoginRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(LoginRepositoryImpl.class);

    @Override
    @Transactional
    public UsuarioEntity obterLogin(String email){
        logger.info(Constantes.DebugBuscarProcesso);
        try {
            String sql = "SELECT * FROM validar_login(?)";
            return jdbcTemplate.queryForObject(sql, new Object[] { email }, new LoginRowMapper());

        } catch (DataAccessException e){
            logger.error(Constantes.ErroBuscarRegistroNoServidor, e.getMessage());
            throw new ObterLoginNotFoundException();
        }
    }
}
