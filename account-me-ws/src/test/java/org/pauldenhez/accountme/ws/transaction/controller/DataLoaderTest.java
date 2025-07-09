package org.pauldenhez.accountme.ws.transaction.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.pauldenhez.accountme.common.model.transaction.Transaction;
import org.pauldenhez.accountme.ws.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@TestConfiguration
public class DataLoaderTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @PostConstruct
    public void loadData() throws Exception {
        var objectMapper = new ObjectMapper();
        URL resource = TransactionDTOControllerTest.class.getClassLoader().getResource("data.json");
        String json = Files.readString(Path.of(resource.toURI()));
        List<Transaction> transactions = objectMapper.readValue(json, new TypeReference<>() {});
        transactionRepository.saveAll(transactions);
    }
}