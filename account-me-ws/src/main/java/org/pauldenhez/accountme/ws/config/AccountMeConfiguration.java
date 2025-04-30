package org.pauldenhez.accountme.ws.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.pauldenhez.accountme.*"})
public class AccountMeConfiguration /*extends ElasticsearchConfiguration*/ {
    // Inutile pour l'instant

}
