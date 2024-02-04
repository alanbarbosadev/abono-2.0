package com.dataprev.abono.customs;

import com.dataprev.abono.utils.Util;
import lombok.Getter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileItemWriter;

import java.util.Date;

@Getter
public class PagamentoReportWriterCustom<T> extends FlatFileItemWriter<T> {
    private StepExecution stepExecution;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    public int getTotalPagamentos() {
        return stepExecution.getExecutionContext().getInt("stepTotalPagamentos");
    }

    public double getValorTotalPagamentos() {
        return stepExecution.getExecutionContext().getDouble("stepValorTotalPagamentos");
    }

    public String getHeader() {
        var header = new StringBuilder();

        header.append(Util.IDENTIFICACAO_REGISTRO_CABECALHO);
        header.append(Util.formatDate(new Date()));
        header.append(Util.CODIGO_ARQUIVO);
        header.append(Util.formatField(Util.NOME_ARQUIVO, Util.NOME_ARQUIVO_SIZE));
        header.append(Util.NUMERO_LOTE);
        var zerosToAdd = 280 - header.length();

        for(int i = 0; i < zerosToAdd; i++) {
            header.append("0");
        }

        return header.toString();
    }

    public String getFooter() {
        var footer = new StringBuilder();

        footer.append(Util.IDENTIFICACAO_REGISTO_RODAPE);
        footer.append(Util.formatField(getTotalPagamentos(), Util.QUANTIDADE_PAGAMENTOS_SIZE));
        footer.append(Util.formatField(getValorTotalPagamentos(), Util.VALOR_TOTAL_PAGAMENTOS_SIZE));
        var zerosToAdd = 280 - footer.length();

        for(int i = 0; i < zerosToAdd; i++) {
            footer.append("0");
        }

        return footer.toString();
    }

    @Override
    public void write(Chunk<? extends T> items) throws Exception {
        super.write(items);
    }
}
