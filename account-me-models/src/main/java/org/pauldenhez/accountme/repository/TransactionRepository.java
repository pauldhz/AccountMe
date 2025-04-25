package org.pauldenhez.accountme.repository;

import lombok.NonNull;
import org.pauldenhez.accountme.model.Transaction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends ElasticsearchRepository<Transaction, String> {

    @NonNull
    List<Transaction> findAll();

}
