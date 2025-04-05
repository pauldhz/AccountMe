package org.pauldenhez.accountme.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.InfoResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ElasticReaderService {

    private final ElasticsearchClient elasticsearchClient;

    public ElasticReaderService(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public InfoResponse read() throws IOException {
        return this.elasticsearchClient.info();
    }

}
