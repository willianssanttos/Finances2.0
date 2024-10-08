package br.com.sistema.controle.financas.pessoais.adapter.output.mapper;

import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ContaEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ObterContasRowMapper implements RowMapper<ContaEntity> {

    public ContaEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ContaEntity conta = new ContaEntity();
        conta.setIdConta(rs.getInt(1));
        conta.setIdSaldo(rs.getInt(2));
        conta.setTipoConta(rs.getString(3));
        conta.setNomeConta(rs.getString(4));
        conta.setSaldoConta(rs.getDouble(5));
        conta.setDataDeposito(Timestamp.valueOf(rs.getTimestamp(6).toLocalDateTime()));
        return conta;
    }
}
