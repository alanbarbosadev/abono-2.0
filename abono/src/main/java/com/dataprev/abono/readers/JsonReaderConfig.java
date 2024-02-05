package com.dataprev.abono.readers;

import com.dataprev.abono.models.Pagamento;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class JsonReaderConfig {

    @Bean
    public JsonItemReader<Pagamento> jsonReader(String path) {
        return new JsonItemReaderBuilder<Pagamento>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(Pagamento.class))
                .resource(new ClassPathResource("pagamento.json"))
                .name("jsonReader")
                .build();
    }
}
