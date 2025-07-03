package org.pauldenhez.accountme.ws.config;

import org.pauldenhez.accountme.common.model.transaction.vo.DoubleToPositiveAmountConverter;
import org.pauldenhez.accountme.common.model.transaction.vo.PositiveAmountToDoubleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

import java.util.Arrays;

@Configuration
public class ElasticSearchConfiguration extends ElasticsearchConfiguration {

    private final Environment environment;

    public ElasticSearchConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder() //
                .connectedTo(environment.getProperty("spring.elasticsearch.uris", "localhost:9200")) //
                .build();
    }

    @Bean
    @Override
    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
        return new ElasticsearchCustomConversions(
                Arrays.asList(new PositiveAmountToDoubleConverter(), new DoubleToPositiveAmountConverter()));
    }
}
