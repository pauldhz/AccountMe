package org.pauldenhez.accountme.batch.repository;

import org.pauldenhez.accountme.common.model.transaction.Transaction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TransactionRepository extends ElasticsearchRepository<Transaction, String> {
}
