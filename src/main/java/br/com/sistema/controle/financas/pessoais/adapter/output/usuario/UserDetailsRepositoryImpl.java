package br.com.sistema.controle.financas.pessoais.adapter.output.usuario;

import br.com.sistema.controle.financas.pessoais.domain.command.security.UserDetailsImpl;
import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.domain.exception.CriarUsuarioException;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class UserDetailsRepositoryImpl implements UserDetailsService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity user = jdbcTemplate.queryForObject("SELECT * FROM validar_login(?)",
                new Object[]{email},
                (rs, rowNum) -> {
                    UsuarioEntity u = new UsuarioEntity();
                    u.setIdUsuario(rs.getInt(1));
                    u.setEmailUsuario(rs.getString(2));
                    u.setSenhaUsuario(rs.getString(3));
                    return u;
                });

        if (user == null) {
            throw new IllegalAccessError(Constantes.erroLoginConta + email);
        }

        return UserDetailsImpl.build(user);
    }
}
