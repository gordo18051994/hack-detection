package com.fernandonieto.hackdetection.domain.port.input;

import com.fernandonieto.hackdetection.domain.port.output.LogFileRepository;
import com.fernandonieto.hackdetection.domain.validator.EntryValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HackerDetectorConfig {

    @Bean
    public HackerDetector hackerDetector(final LogFileRepository repository, final EntryValidator validator) {
        return new HackerDetectorImpl(repository, validator);
    }

}
