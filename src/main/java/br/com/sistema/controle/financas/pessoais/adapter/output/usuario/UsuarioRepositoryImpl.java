package br.com.sistema.controle.financas.pessoais.adapter.output.usuario;

import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.domain.exception.CriarUsuarioException;
import br.com.sistema.controle.financas.pessoais.domain.exception.EmailExistenteException;
import br.com.sistema.controle.financas.pessoais.port.output.usuario.IUsuarioRepository;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioRepositoryImpl.class);

    @Override
    @Transactional
    public UsuarioEntity criarUsuario(UsuarioEntity usuario){
        logger.info(Constantes.DebugRegistroProcesso);
        try {
            String sql = "SELECT inserir_usuario(?,?,?,?)";
            Integer IdUsuario = jdbcTemplate.queryForObject(sql, new Object[]{
                    usuario.getNomeUsuario(),
                    usuario.getEmailUsuario(),
                    usuario.getSenhaUsuario(),
                    usuario.getNumeroCelular()
            },Integer.class);
            usuario.setIdUsuario(IdUsuario);
        } catch (DataAccessException e){
            logger.error(Constantes.ErroRegistrarNoServidor);
            throw new CriarUsuarioException();
        }
        return usuario;
    }

    @Override
    @Transactional
    public boolean verificarEmailExistente(String email) {
        logger.info(Constantes.DebugBuscarProcesso);
        String sql = "SELECT verificar_email_existente(?)";
        try{
        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
        } catch (DataAccessException e) {
            logger.error(Constantes.ErroBuscarRegistroNoServidor);
            throw new EmailExistenteException();
        }
    }
}
