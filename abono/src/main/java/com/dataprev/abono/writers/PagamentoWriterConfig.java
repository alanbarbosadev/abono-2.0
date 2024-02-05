package com.dataprev.abono.writers;

import com.dataprev.abono.models.Pagamento;
import com.dataprev.abono.repositories.PagamentoRepository;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoWriterConfig {

    @Bean
    public RepositoryItemWriter<Pagamento> writer(PagamentoRepository pagamentoRepository) {
        RepositoryItemWriter<Pagamento> writer = new RepositoryItemWriter<>();
        writer.setRepository(pagamentoRepository);
        writer.setMethodName("save");

        return writer;
    }
}
