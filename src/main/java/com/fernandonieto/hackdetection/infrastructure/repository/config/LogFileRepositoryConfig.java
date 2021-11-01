package com.fernandonieto.hackdetection.infrastructure.repository.config;

import com.fernandonieto.hackdetection.domain.port.output.LogFileRepository;
import com.fernandonieto.hackdetection.infrastructure.repository.LogFileRepositoryImpl;
import com.fernandonieto.hackdetection.infrastructure.repository.adapter.LogFileRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class LogFileRepositoryConfig {

    @Bean
    public LogFileRepository logFileRepository(final LogFileRepositoryAdapter adapter, final MongoTemplate mongoTemplate) {
        return new LogFileRepositoryImpl(mongoTemplate, adapter);
    }

}
