package com.dataprev.abono.config;

import com.dataprev.abono.customs.PagamentoReportWriterCustom;
import com.dataprev.abono.dtos.PagamentoReportDto;
import com.dataprev.abono.errors.ReadErrorListener;
import com.dataprev.abono.errors.WriteErrorListener;
import com.dataprev.abono.models.Pagamento;
import com.dataprev.abono.mappers.PagamentoDatabaseRowMapper;
import com.dataprev.abono.processors.PagamentoProcessor;
import com.dataprev.abono.readers.DatabaseReaderConfig;
import com.dataprev.abono.readers.JsonReaderConfig;
import com.dataprev.abono.repositories.PagamentoRepository;
import com.dataprev.abono.utils.Util;
import com.dataprev.abono.writers.PagamentoWriterConfig;
import com.dataprev.abono.writers.ReportWriterConfig;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class PagamentoAbonoReportJobConfig {

    private final PagamentoRepository pagamentoRepository;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final DataSource dataSource;
    private final ReadErrorListener readErrorListener;
    private final WriteErrorListener writeErrorListener;
    private final JsonReaderConfig jsonReaderConfig;
    private final PagamentoWriterConfig pagamentoWriterConfig;
    private final DatabaseReaderConfig databaseReaderConfig;
    private final ReportWriterConfig reportWriterConfig;


    @Bean
    public PagamentoProcessor processor() {
        return new PagamentoProcessor();
    }


    @Bean
    public Step importStep() {
        return new StepBuilder("Json_Import", jobRepository).
                <Pagamento, Pagamento>chunk(10, platformTransactionManager)
                .reader(jsonReaderConfig.jsonReader("pagamento.json"))
                .writer(pagamentoWriterConfig.writer(pagamentoRepository))
                .faultTolerant()
                .skip(Throwable.class)
                .skipPolicy(new AlwaysSkipItemSkipPolicy())
                .listener(readErrorListener)
                .build();
    }

    @Bean
    public Step exportStep() {
        return new StepBuilder("Txt_Export", jobRepository)
                .<Pagamento, PagamentoReportDto>chunk(10, platformTransactionManager)
                .reader(databaseReaderConfig.databaseReader(dataSource, Util.GET_ALL_PAYMENTS_QUERY,
                        new PagamentoDatabaseRowMapper()))
                .processor(processor())
                .writer(reportWriterConfig.reportWriter())
                .faultTolerant()
                .skip(Throwable.class)
                .skipPolicy(new AlwaysSkipItemSkipPolicy())
                .listener(writeErrorListener)
                .build();
    }

    @Bean
    public Job pagamentoAbonoReportJob() {
        return new JobBuilder("Pagamento_Abono_Report_Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(importStep())
                .next(exportStep())
                .build();
    }
}
