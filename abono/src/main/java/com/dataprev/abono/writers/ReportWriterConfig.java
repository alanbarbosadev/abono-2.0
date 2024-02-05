package com.dataprev.abono.writers;

import com.dataprev.abono.customs.PagamentoReportWriterCustom;
import com.dataprev.abono.dtos.PagamentoReportDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ReportWriterConfig {
    @Bean
    public ItemWriter<PagamentoReportDto> reportWriter() {
        PagamentoReportWriterCustom<PagamentoReportDto> writer = new PagamentoReportWriterCustom<>();
        writer.setResource(new FileSystemResource("src/main/resources/pagamento.txt"));
        writer.setLineAggregator(new DelimitedLineAggregator<>() {
            {
                setDelimiter("");
                setFieldExtractor(new BeanWrapperFieldExtractor<>() {
                    {
                        setNames(new String[]{"identificacaoRegistro", "codigoPagamento", "exercicioFinanceiro", "anoBase", "pisPasep", "nome",  "nascimento",
                                "cpf", "nomeMae", "numeroParcela", "valorPagamento","mesesTrabalhados","dataInicialPagamento", "dataFinalPagamento", "numeroSentenca","banco",
                                "agencia", "digitoVerificador", "tipoConta", "conta", "indicadorPagamento", "zeros"});
                    }
                });
            }
        });
        writer.setHeaderCallback(x -> x.write(writer.getHeader()));
        writer.setFooterCallback(x -> x.write(writer.getFooter()));
        writer.setShouldDeleteIfExists(true);
        return writer;
    }
}
