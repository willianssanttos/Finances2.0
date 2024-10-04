package br.com.sistema.controle.financas.pessoais.adapter.output.conta;

import br.com.sistema.controle.financas.pessoais.port.output.conta.ISaldoRepository;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.SaldoEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SaldoRepositoryImpl implements ISaldoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SaldoRepositoryImpl.class);

    @Override
    @Transactional
    public SaldoEntity inserirSaldo(SaldoEntity saldo){
        logger.info(Constantes.DebugRegistroProcesso);

        try{
        String sql = "SElECT inserir_saldo(?,?,?)";
        Integer idSaldo = jdbcTemplate.queryForObject(sql, new Object[]{
                saldo.getIdUsuario(),
                saldo.getSaldoAtual(),
                saldo.getDataAtualizadaSaldo()
        }, Integer.class);
        saldo.setIdSaldo(idSaldo);
        } catch (Exception e){
           logger.error(Constantes.ErroRegistrarNoServidor);
        }
        return saldo;
    }
}
