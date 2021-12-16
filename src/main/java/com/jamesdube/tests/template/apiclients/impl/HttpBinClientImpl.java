package com.jamesdube.tests.template.apiclients.impl;

import com.jamesdube.tests.template.apiclients.api.HttpBinClient;
import com.jamesdube.tests.template.data.BinData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.jamesdube.tests.template.utils.Utils.toJson;

@Slf4j
public class HttpBinClientImpl implements HttpBinClient {

    private final WebClient webClient;

    @Value("${app.http.url}")
    private String appUrl;

    public HttpBinClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public BinData post(BinData binData) {

        BinData binDataMono = webClient.post().uri(appUrl + "/post")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(binData)
                .retrieve()
                .bodyToMono(BinData.class)
                .block();

        log.info("http-bin request : {}",toJson(binData));

        return binDataMono;
    }
}
