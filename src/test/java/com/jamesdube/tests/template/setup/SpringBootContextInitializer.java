package com.jamesdube.tests.template.setup;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringBootContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

        WireMockSetup.setUp();
        WireMockSetup.registerComponent(configurableApplicationContext);

        TestPropertyValues values = TestPropertyValues.of(
                "app.http.url=" + WireMockSetup.getBaseUrl()
        );

        values.applyTo(configurableApplicationContext);
    }
}
