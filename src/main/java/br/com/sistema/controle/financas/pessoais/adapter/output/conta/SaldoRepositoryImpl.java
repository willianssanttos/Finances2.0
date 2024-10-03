package br.com.sistema.controle.financas.pessoais.adapter.output.conta;

import br.com.sistema.controle.financas.pessoais.port.output.conta.ISaldoRepository;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.SaldoEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SaldoRepositoryImpl implements ISaldoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SaldoRepositoryImpl.class);

    @Override
    public SaldoEntity inserirSaldo(SaldoEntity saldo){
        logger.debug(Constantes.DebugRegistroProcesso + saldo);

        try{
        String sql = "SElECT inserir_saldo(?,?,?)";
        Integer idSaldo = jdbcTemplate.queryForObject(sql, new Object[]{ saldo.getIdUsuario(), saldo.getSaldoAtual(), saldo.getDataAtualizadaSaldo()
        }, Integer.class);
        saldo.setIdSaldo(idSaldo);
        } catch (Exception e){
           logger.error(Constantes.ErroRegistrarNoServidor);
        }
        return saldo;
    }

//    public Double obterSaldoPorIdUsuario(Integer idUsuario) {
//        logger.debug(Constantes.DebugBuscarProcesso + idUsuario);
//
//        String sql = "SELECT obter_saldo_total(?)";
//
//        double saldoTotal = 0.0;
//        try (Connection conn = DataSourceConfig.getConexao();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, idUsuario);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()){
//                saldoTotal = rs.getDouble(1);
//            }
//            rs.close();
//
//            logger.info(Constantes.InfoBuscar + idUsuario);
//        } catch (SQLException e) {
//            logger.error(Constantes.ErroBuscarRegistroNoServidor);
//        }
//        return saldoTotal;
//    }
}
