package org.pauldenhez.AccountMe.controller;

import org.pauldenhez.AccountMe.model.Transaction;
import org.pauldenhez.AccountMe.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping(path = "test")
    public ResponseEntity<List<Transaction>> testlala() {
        Iterable<Transaction> iterable = transactionRepository.findAll();
        for(Transaction transaction : iterable) {
            System.out.println(transaction);
        }

        return ResponseEntity.ok(Streamable.of(iterable).toList());
    }

    @GetMapping(path = "/add")
    public ResponseEntity<Transaction> add() {
        Transaction savedTransaction = new Transaction();
        savedTransaction.setTransactionDate(new Date());
        savedTransaction.setLabel("ICI un label de CB");
        savedTransaction.setDebit(12.99);
        transactionRepository.save(savedTransaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable String id) {
        Optional<Transaction> tran = transactionRepository.findById(id);
        if(tran.isPresent()) {
            Transaction savedTransaction = tran.get();
            savedTransaction.setDebit(tran.get().getDebit()+1);
            transactionRepository.save(savedTransaction);
        }
        return tran.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
