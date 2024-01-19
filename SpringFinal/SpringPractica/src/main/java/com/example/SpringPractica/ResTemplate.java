package com.example.SpringPractica;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResTemplate {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
