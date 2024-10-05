package br.com.sistema.controle.financas.pessoais.domain.command.transacao;

import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.request.TransacaoRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.transacao.response.TransacaoResponse;
import br.com.sistema.controle.financas.pessoais.domain.command.Enum.CategoriaEnum;
import br.com.sistema.controle.financas.pessoais.domain.exception.CriarTransacaoException;
import br.com.sistema.controle.financas.pessoais.port.input.transacao.ITransacao;
import br.com.sistema.controle.financas.pessoais.port.output.transacao.ITransacaoContaRepository;
import br.com.sistema.controle.financas.pessoais.domain.entity.transacao.TransacoesContaEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class TransacaoService implements ITransacao {

    @Autowired
    private ITransacaoContaRepository iTransacaoContaRepository;
    private static final Logger logger = LoggerFactory.getLogger(TransacaoService.class);


    public TransacaoResponse registrarTransacao(TransacaoRequest transacao){
        try {

            TransacoesContaEntity novaTransacao = TransacoesContaEntity.builder()
                    .idConta(transacao.getIdConta())
                    .idSaldo(transacao.getIdSaldo())
                    .Descricao(transacao.getDescricao())
                    .Categoria(String.valueOf(CategoriaEnum.valueOf(transacao.getCategoria())))
                    .Valor(transacao.getValor())
                    .tipo(transacao.getTipo())
                    .dataMovimentacao(Timestamp.valueOf(LocalDateTime.now()))
                    .build();

            TransacoesContaEntity realizarTransacao = iTransacaoContaRepository.inserirTransacao(novaTransacao);

            return mapearTransacao(realizarTransacao);
        } catch (Exception e){
            logger.error(Constantes.ErrocadastroTransacao);
            throw new CriarTransacaoException();
        }
    }

    private TransacaoResponse mapearTransacao(TransacoesContaEntity realizarTransacao){
        return TransacaoResponse.builder()
                .Descricao(realizarTransacao.getDescricao())
                .Categoria(String.valueOf(CategoriaEnum.valueOf(realizarTransacao.getCategoria())))
                .Valor(realizarTransacao.getValor())
                .tipo(realizarTransacao.getTipo())
                .dataMovimentacao(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }
//    public void registrarTransacao(Integer idConta, Integer idSaldo, String descricao, String categoria, Double valor, int tipo){
//           try {
//               if (tipo != 1 && tipo != 2){
//                   logger.error(Constantes.tipoTransacao);
//               }
//
//               TransacoesContaEntity novaTransacao = new TransacoesContaEntity();
//               novaTransacao.setIdConta(idConta);
//               novaTransacao.setIdSaldo(idSaldo);
//               novaTransacao.setDescricao(descricao);
//               novaTransacao.setCategoria(categoria);
//               novaTransacao.setValor(valor);
//               novaTransacao.setTipo(tipo);
//               novaTransacao.setDataMovimentacao(Timestamp.valueOf(LocalDateTime.now()));
//
//               ITransacaoContaDao.inserirTransacao(novaTransacao);
//           } catch (Exception e){
//               logger.error(Constantes.ErrocadastroTransacao);
//           }
//    }

//    public List<ExtratoEntity> obterExtratoPorMes(Integer idUsuario, int mes, int ano){
//        try {
//            return ITransacaoContaDao.obterExtratoPorMes(idUsuario, mes, ano);
//        } catch (Exception e){
//            logger.error(Constantes.ErrorRecuperarExtrato);
//        }
//        return obterExtratoPorMes(idUsuario, mes, ano);
//    }
}
