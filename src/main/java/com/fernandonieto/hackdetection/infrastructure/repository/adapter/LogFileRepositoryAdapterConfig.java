package com.fernandonieto.hackdetection.infrastructure.repository.adapter;

import com.fernandonieto.hackdetection.infrastructure.repository.adapter.mapper.LogFileRepositoryMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogFileRepositoryAdapterConfig {

    @Bean
    public LogFileRepositoryAdapter logFileRepositoryAdapter() {
        return new LogFileRepositoryAdapterImpl(LogFileRepositoryMapper.INSTANCE);
    }

}
