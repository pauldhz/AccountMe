package org.pauldenhez.accountme.controller;

import org.pauldenhez.accountme.batch.model.Transaction;
import org.pauldenhez.accountme.batch.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping(path = "/transactions")
    public ResponseEntity<List<Transaction>> listAll() {
        final var transactions = transactionRepository.findAll();
        transactions.forEach(System.out::println);
        return ResponseEntity.ok(transactions);
    }

    @PutMapping(path = "/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping(path = "/transactions/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable String id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/transactions/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String id) {
        transactionRepository.deleteById(id);
        return ResponseEntity.ok(String.format("Document %s removed", id));
    }

}
