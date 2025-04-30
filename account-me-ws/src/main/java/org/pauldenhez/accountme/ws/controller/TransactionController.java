package org.pauldenhez.accountme.ws.controller;

import org.pauldenhez.accountme.common.model.Transaction;
import org.pauldenhez.accountme.ws.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping(path = "/transactions")
    public ResponseEntity<List<Transaction>> listAll() {
        final var transactions = transactionRepository.findAll();
        return ResponseEntity.ok(transactions);
    }

    @PutMapping(path = "/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @PostMapping(path = "/transactions")
    public ResponseEntity<Boolean> createTransactions(@RequestBody Transaction[] transactions) {
        transactionRepository.saveAll(Arrays.asList(transactions));
        return ResponseEntity.ok(true);
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
