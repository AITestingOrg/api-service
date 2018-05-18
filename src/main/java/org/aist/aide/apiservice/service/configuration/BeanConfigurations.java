package org.aist.aide.apiservice.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfigurations {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
