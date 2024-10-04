package br.com.sistema.controle.financas.pessoais.adapter.output.transacao;

import br.com.sistema.controle.financas.pessoais.domain.exception.CriarTransacaoException;
import br.com.sistema.controle.financas.pessoais.port.output.transacao.ITransacaoContaRepository;
//import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ExtratoEntity;
import br.com.sistema.controle.financas.pessoais.domain.entity.transacao.TransacoesContaEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TransacaoContaRepositoryImpl implements ITransacaoContaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(TransacaoContaRepositoryImpl.class);
    @Override
    @Transactional
    public TransacoesContaEntity inserirTransacao(TransacoesContaEntity transacao){
        logger.info(Constantes.DebugRegistroProcesso);

        try {
            String sql = "SELECT inserir_transacao(?,?,?,?,?,?,?)";
            Integer idTransacao = jdbcTemplate.queryForObject(sql, new Object[] {
                    transacao.getIdSaldo(),
                    transacao.getIdConta(),
                    transacao.getDescricao(),
                    transacao.getCategoria(),
                    transacao.getValor(),
                    transacao.getDataMovimentacao(),
                    transacao.getTipo()
            }, Integer.class);
            transacao.setIdTransacao(idTransacao);

        } catch (DataAccessException e){
            logger.error(Constantes.ErroRegistrarNoServidor);
            throw new CriarTransacaoException();
        }
        return transacao;
    }

//    public List<ExtratoEntity> obterExtratoPorMes(Integer idUsuario, int mes, int ano){
//        logger.debug(Constantes.DebugBuscarProcesso + idUsuario);
//
//        String sql = "SELECT * FROM buscar_extrato_por_usuario(?,?,?)";
//
//        List<ExtratoEntity> extratos = new ArrayList<>();
//
//        try (Connection conn = DataSourceConfig.getConexao();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, idUsuario);
//            ps.setInt(2, mes);
//            ps.setInt(3, ano);
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                ExtratoEntity extrato = new ExtratoEntity();
//                extrato.setNomeConta(rs.getString("nm_nome"));
//                extrato.setDescricao(rs.getString("ds_descricao"));
//                extrato.setCategoria(rs.getString("ds_categoria"));
//                extrato.setValor(rs.getDouble("ds_valor"));
//                extrato.setDataMovimentacao(rs.getTimestamp("ds_data_movimentacao"));
//                extrato.setTipoTransacao(rs.getInt("tipo") == 1 ? "Ganho" : "Gasto");
//                extrato.setTipoConta(rs.getString("nm_tipo_conta"));
//                extratos.add(extrato);
//            }
//
//            logger.info(Constantes.InfoBuscar + idUsuario);
//        } catch (SQLException e){
//            logger.error(Constantes.ErroBuscarRegistroNoServidor);
//        }
//        return extratos;
//    }
}
