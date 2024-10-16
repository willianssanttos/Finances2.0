package br.com.sistema.controle.financas.pessoais.adapter.output.mapper;

import br.com.sistema.controle.financas.pessoais.domain.entity.conta.TipoContaEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoContaRowMapper implements RowMapper<TipoContaEntity> {
    public TipoContaEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        TipoContaEntity tipoConta = new TipoContaEntity();
        tipoConta.setIdTipoConta(rs.getInt(1));
        tipoConta.setNomeTipoConta(rs.getString(2));
        return tipoConta;
    }
}
