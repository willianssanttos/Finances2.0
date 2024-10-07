package br.com.sistema.controle.financas.pessoais.adapter.input.transacao.response;
import br.com.sistema.controle.financas.pessoais.domain.entity.transacao.ExtratoTransacaoEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ExtratoTransacaoResponse{

    private double totalGanhos;
    private double totalGastos;
    private Map<String, Double> ganhosPorCategoria;
    private Map<String, Double> gastosPorCategoria;
    private List<ExtratoTransacaoEntity> extratos;

    public ExtratoTransacaoResponse(double totalGanhos, double totalGastos,
                                    Map<String, Double> ganhosPorCategoria,
                                    Map<String, Double> gastosPorCategoria,
                                    List<ExtratoTransacaoEntity> extratos) {
        this.totalGanhos = totalGanhos;
        this.totalGastos = totalGastos;
        this.ganhosPorCategoria = ganhosPorCategoria;
        this.gastosPorCategoria = gastosPorCategoria;
        this.extratos = extratos;
    }

    public static ExtratoTransacaoResponse converterExtrato(List<ExtratoTransacaoEntity> extratos) {
        Map<String, Double> ganhosPorCategoria = new HashMap<>();
        Map<String, Double> gastosPorCategoria = new HashMap<>();
        double totalGanhos = 0;
        double totalGastos = 0;

        for (ExtratoTransacaoEntity extrato : extratos) {
            if ("Ganho".equals(extrato.getTipoTransacao())) {
                totalGanhos += extrato.getValor();
                ganhosPorCategoria.merge(extrato.getCategoria(), extrato.getValor(), Double::sum);
            } else {
                totalGastos += extrato.getValor();
                gastosPorCategoria.merge(extrato.getCategoria(), extrato.getValor(), Double::sum);
            }
        }
        return new ExtratoTransacaoResponse(totalGanhos, totalGastos, ganhosPorCategoria, gastosPorCategoria, extratos);
    }
}
