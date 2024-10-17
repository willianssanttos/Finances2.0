package br.com.sistema.controle.financas.pessoais.adapter.output.mapper;

import br.com.sistema.controle.financas.pessoais.domain.entity.transacao.ExtratoTransacaoEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtratoTransacaoRowMapper implements RowMapper<ExtratoTransacaoEntity> {
    public ExtratoTransacaoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExtratoTransacaoEntity extrato = new ExtratoTransacaoEntity();
        extrato.setNomeConta(rs.getString(1));
        extrato.setDescricao(rs.getString(2));
        extrato.setCategoria(rs.getString(3));
        extrato.setValor(rs.getDouble(4));
        extrato.setDataMovimentacao(rs.getTimestamp(5).toLocalDateTime());
        extrato.setTipoTransacao(rs.getInt(6) == 1 ? "Ganho" : "Gasto");
        extrato.setTipoConta(rs.getString(7));
        return extrato;
    }
}
