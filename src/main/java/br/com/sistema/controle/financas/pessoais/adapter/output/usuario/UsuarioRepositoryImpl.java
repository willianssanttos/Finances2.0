package br.com.sistema.controle.financas.pessoais.adapter.output.usuario;

import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.port.output.usuario.IUsuarioDao;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UsuarioRepositoryImpl implements IUsuarioDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioRepositoryImpl.class);

    @Override
    public UsuarioEntity criarUsuario(UsuarioEntity usuario){
        try {
            String sql = "SELECT inserir_usuario(?,?,?,?)";
            Integer IdUsuario = jdbcTemplate.queryForObject(sql, new Object[]{
                    usuario.getNomeUsuario(), usuario.getEmailUsuario(), usuario.getSenhaUsuario(),usuario.getNumeroCelular()
            },Integer.class);
            usuario.setIdUsuario(IdUsuario);

        } catch (Exception e){
            logger.error(Constantes.ErroRegistrarNoServidor);
        }
        return usuario;
    }
    public boolean verificarEmailExistente(String email) {
        String sql = "SELECT verificar_email_existente(?)";
        try{
        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
        } catch (Exception e) {
            logger.error(Constantes.ErroBuscarRegistroNoServidor);
        }
        return verificarEmailExistente(email);
    }

}
