package com.fernandonieto.hackdetection.domain.validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntryValidatorConfig {

    @Bean
    public EntryValidator entryValidator() {
        return new EntryValidatorImpl();
    }

}
