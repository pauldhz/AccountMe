package org.pauldenhez.accountme.common.repository;

import lombok.NonNull;
import org.pauldenhez.accountme.common.model.Transaction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Deprecated
public interface TransactionRepository extends ElasticsearchRepository<Transaction, String> {

    @NonNull
    List<Transaction> findAll();

}
