package br.com.sistema.controle.financas.pessoais.adapter.output.mapper;

import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.RolesEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolesMapper implements RowMapper<RolesEntity> {

    public RolesEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        RolesEntity role = new RolesEntity();
        role.setIdRole(rs.getInt(1));
        role.setNomeRole(rs.getString(2));
        return role;
    }
}
