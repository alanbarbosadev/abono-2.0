package com.dataprev.abono.processors;

import com.dataprev.abono.dtos.PagamentoReportDto;
import com.dataprev.abono.models.Pagamento;
import com.dataprev.abono.utils.Util;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;

public class PagamentoProcessor implements ItemProcessor<Pagamento, PagamentoReportDto> {
    private int totalCount;
    private BigDecimal totalValor;
    private StepExecution stepExecution;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
        this.totalCount = 0;
        this.totalValor = BigDecimal.ZERO;
    }
    @Override
    public PagamentoReportDto process(Pagamento pagamento) throws Exception {
        totalValor = totalValor.add(pagamento.getValorPagamento());
        totalCount++;

        return mapPagamentoToPagamentoReportDto(pagamento);
    }

    @AfterStep
    public void afterStep() {
        stepExecution.getExecutionContext().putInt("stepTotalPagamentos", totalCount);
        stepExecution.getExecutionContext().putDouble("stepValorTotalPagamentos", totalValor.doubleValue());
    }

    private PagamentoReportDto mapPagamentoToPagamentoReportDto(Pagamento pagamento) {

        var pagamentoReportDto = new PagamentoReportDto();

        pagamentoReportDto.setIdentificacaoRegistro(Util.IDENTIFICACAO_REGISTO_PAGAMENTO);
        pagamentoReportDto.setCodigoPagamento(Util.formatField(pagamento.getCodigoPagamento(), Util.CODIGO_PAGAMENTO_SIZE));
        pagamentoReportDto.setExercicioFinanceiro(Util.EXERCICIO_FINANCEIRO);
        pagamentoReportDto.setAnoBase(Util.ANO_BASE);
        pagamentoReportDto.setNumeroParcela(Util.NUMERO_PARCELA);
        pagamentoReportDto.setValorPagamento(Util.formatField(pagamento.getValorPagamento(), Util.VALOR_PAGAMENTO_SIZE));
        pagamentoReportDto.setMesesTrabalhados(Util.formatField(pagamento.getMesesTrabalhados(), Util.MESES_TRABALHADOS_SIZE));
        pagamentoReportDto.setDataInicialPagamento(Util.DATA_INICIAL_PAGAMENTO);
        pagamentoReportDto.setDataFinalPagamento(Util.DATA_FINAL_PAGAMENTO);
        pagamentoReportDto.setNumeroSentenca(Util.NUMERO_SENTENCA_JUDICIAL);
        pagamentoReportDto.setBanco(Util.formatField(pagamento.getBanco().getBanco(), Util.BANCO_SIZE));
        pagamentoReportDto.setAgencia(Util.formatField(pagamento.getBanco().getAgencia(), Util.AGENCIA_SIZE));
        pagamentoReportDto.setDigitoVerificador(Util.formatField(pagamento.getBanco().getDigitoVerificador(), Util.DIGITO_VERIFICADOR_SIZE));
        pagamentoReportDto.setTipoConta(Util.formatField(pagamento.getBanco().getTipoConta(), Util.TIPO_CONTA_SIZE));
        pagamentoReportDto.setConta(Util.formatField(pagamento.getBanco().getConta(), Util.CONTA_SIZE));
        pagamentoReportDto.setIndicadorPagamento(Util.formatField(pagamento.getBanco().getIndicadorPagamento(), Util.INDICADOR_PAGAMENTO_SIZE));
        pagamentoReportDto.setCpf(Util.formatField(pagamento.getTrabalhador().getCpf(), Util.CPF_SIZE));
        pagamentoReportDto.setNome(Util.formatField(pagamento.getTrabalhador().getNome(), Util.NOME_SIZE));
        pagamentoReportDto.setNomeMae(Util.formatNomeMae(pagamento.getTrabalhador().getNomeMae(), Util.NOME_MAE_SIZE));
        pagamentoReportDto.setNascimento(Util.formatDate(pagamento.getTrabalhador().getNascimento()));
        pagamentoReportDto.setPisPasep(Util.formatField(pagamento.getTrabalhador().getPisPasep(), Util.PIS_PASEP_SIZE));
        pagamentoReportDto.setZeros("0000000");

         return pagamentoReportDto;
    }
}
