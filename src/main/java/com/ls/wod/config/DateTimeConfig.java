package com.ls.wod.config;

import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

/**
 *
 * @author francisco
 */

@Configuration
public class DateTimeConfig {

    @Bean
    public FormattingConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        DateTimeFormatterRegistrar register = new DateTimeFormatterRegistrar();
        register.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        register.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        register.registerFormatters(conversionService);

        // other desired formatters
        return conversionService;
    }
}
