package org.pauldenhez.AccountMe.repository;

import org.pauldenhez.AccountMe.model.Transaction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends ElasticsearchRepository<Transaction, String> {
    List<Transaction> findAll();

}
