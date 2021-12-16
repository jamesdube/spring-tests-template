package com.jamesdube.tests.template.config;

import com.jamesdube.tests.template.apiclients.api.HttpBinClient;
import com.jamesdube.tests.template.apiclients.impl.HttpBinClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    public HttpBinClient httpBinClient(WebClient webClient){
        return new HttpBinClientImpl(webClient);
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .build();
    }
}
