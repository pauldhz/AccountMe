package org.pauldenhez.accountme.ws.config;

import org.pauldenhez.accountme.common.model.transaction.vo.DoubleToPositiveAmountConverter;
import org.pauldenhez.accountme.common.model.transaction.vo.PositiveAmountToDoubleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

import java.util.Arrays;

@Configuration
public class ElasticSearchConfiguration extends ElasticsearchConfiguration {
    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder() //
                .connectedTo("localhost:9200") //
                .build();
    }

    @Bean
    @Override
    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
        return new ElasticsearchCustomConversions(
                Arrays.asList(new PositiveAmountToDoubleConverter(), new DoubleToPositiveAmountConverter()));
    }
}
