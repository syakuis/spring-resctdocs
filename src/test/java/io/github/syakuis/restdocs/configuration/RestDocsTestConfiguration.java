package io.github.syakuis.restdocs.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-08-14
 */
@TestConfiguration
public class RestDocsTestConfiguration {
    @Value("${server.port}")
    private int port;

    @Bean
    public RestDocsMockMvcConfigurationCustomizer restDocsMockMvcConfigurationCustomizer() {
        return configurer -> configurer
            .uris().withPort(port).withHost("localhost").withScheme("http")
            .and()
            .operationPreprocessors()
            .withRequestDefaults(prettyPrint())
            .withResponseDefaults(prettyPrint());
    }
}
