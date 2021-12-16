package com.jamesdube.tests.template.features;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;
import com.github.tomakehurst.wiremock.matching.ContentPattern;
import com.github.tomakehurst.wiremock.matching.PathPattern;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import com.jamesdube.tests.template.AppTests;
import com.jamesdube.tests.template.apiclients.api.HttpBinClient;
import com.jamesdube.tests.template.data.BinData;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.time.ZoneId;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExternalApiTests extends AppTests {

    @Autowired
    private HttpBinClient httpBinClient;

    @Value("${app.http.url}")
    private String appUrl;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("app.http.url", wireMockRule::baseUrl);
    }

    @Test
    @DisplayName("it posts to http bin")
    public void itPostsToHttpBin(){

        //given
        final String name = new Faker().funnyName().name();
        ObjectNode data = new ObjectMapper().createObjectNode();
        data.put("name",name);

        wireMockRule.stubFor(post(urlEqualTo("/post"))
                .withRequestBody(equalTo(data.toString()))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(data.toString()))
        );

        //when
        BinData binData = new BinData();
        binData.setName(name);

        BinData clientData = httpBinClient.post(binData);

        assertNotNull(clientData);
        assertEquals(name,clientData.getName());
        wireMockRule.verify(1, postRequestedFor(urlEqualTo("/post")));


    }
}
