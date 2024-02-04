package com.dataprev.abono.config;

import com.dataprev.abono.customs.PagamentoReportWriterCustom;
import com.dataprev.abono.dtos.PagamentoReportDto;
import com.dataprev.abono.errors.ReadErrorListener;
import com.dataprev.abono.errors.WriteErrorListener;
import com.dataprev.abono.models.Pagamento;
import com.dataprev.abono.mappers.PagamentoDatabaseRowMapper;
import com.dataprev.abono.processors.PagamentoProcessor;
import com.dataprev.abono.repositories.PagamentoRepository;
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
public class PagamentoAbonoReportJobConfig {

    private final PagamentoRepository pagamentoRepository;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final DataSource dataSource;
    private final ReadErrorListener readErrorListener;
    private final WriteErrorListener writeErrorListener;

    @Autowired
    public PagamentoAbonoReportJobConfig(PagamentoRepository pagamentoRepository, JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, DataSource dataSource, ReadErrorListener readErrorListener, WriteErrorListener writeErrorListener) {
        this.pagamentoRepository = pagamentoRepository;
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
        this.dataSource = dataSource;
        this.readErrorListener = readErrorListener;
        this.writeErrorListener = writeErrorListener;
    }

    @Bean
    public JsonItemReader<Pagamento> reader() {
        return new JsonItemReaderBuilder<Pagamento>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(Pagamento.class))
                .resource(new ClassPathResource("pagamento.json"))
                .name("pagamentoJsonItemReader")
                .build();
    }

    @Bean
    public ItemStreamReader<Pagamento> pagamentoReader() {
        JdbcCursorItemReader<Pagamento> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT * FROM tb_pagamento INNER JOIN tb_trabalhador ON tb_pagamento.fk_trabalhador_id = tb_trabalhador.trabalhador_id INNER JOIN tb_banco ON tb_pagamento.fk_banco_id = tb_banco.banco_id");
        reader.setRowMapper(new PagamentoDatabaseRowMapper());
        return reader;
    }

    @Bean
    public PagamentoProcessor processor() {
        return new PagamentoProcessor();
    }

    @Bean
    public RepositoryItemWriter<Pagamento> writer() {
        RepositoryItemWriter<Pagamento> writer = new RepositoryItemWriter<>();
        writer.setRepository(pagamentoRepository);
        writer.setMethodName("save");

        return writer;
    }

    @Bean
    public ItemWriter<PagamentoReportDto> pagamentoWriter() {
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

    @Bean
    public Step importStep() {
        return new StepBuilder("Json_Import", jobRepository).
                <Pagamento, Pagamento>chunk(10, platformTransactionManager)
                .reader(reader())
                .writer(writer())
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
                .reader(pagamentoReader())
                .processor(processor())
                .writer(pagamentoWriter())
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
