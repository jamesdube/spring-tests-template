package com.jamesdube.tests.template;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.jamesdube.tests.template.setup.SpringBootContextInitializer;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@SpringBootTest
@ContextConfiguration(initializers = SpringBootContextInitializer.class)
public class AppTests {

    protected static WireMockServer wireMockRule = new WireMockServer(options().dynamicPort());

    @BeforeAll
    public static void beforeAll() {
        wireMockRule.start();
    }

    @AfterAll
    public static void afterAll() {
        wireMockRule.stop();
    }

    @AfterEach
    public void afterEach() {
        wireMockRule.resetAll();
    }

    @Test
    @DisplayName("it works")
    public void itWorks(){

    }
}
