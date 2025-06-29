package org.pauldenhez.accountme.ws.controller;

import org.pauldenhez.accountme.common.model.transaction.Transaction;
import org.pauldenhez.accountme.common.model.transaction.dto.TransactionResponse;
import org.pauldenhez.accountme.common.model.transaction.mapper.TransactionMapper;
import org.pauldenhez.accountme.ws.repository.TransactionRepository;
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
    public ResponseEntity<List<TransactionResponse>> listAll() {
        final var transactions = transactionRepository.findAll().stream().map(
                (transaction -> TransactionMapper.toDto(transaction, null, null))
        ).toList();
        return ResponseEntity.ok(transactions);
    }

    @PutMapping(path = "/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionResponse transaction) {
        Transaction savedTransaction = transactionRepository.save(TransactionMapper.fromDto(transaction));
        return ResponseEntity.ok(savedTransaction);
    }

    @PostMapping(path = "/transactions")
    public ResponseEntity<Boolean> createTransactions(@RequestBody TransactionResponse[] transactions) {
        transactionRepository.saveAll(Arrays.stream(transactions).map(TransactionMapper::fromDto).toList());
        return ResponseEntity.ok(true);
    }

    @GetMapping(path = "/transactions/{id}")
    public ResponseEntity<TransactionResponse> getById(@PathVariable String id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map((transactionTmp ->
                TransactionMapper.toDto(transactionTmp, null, null)))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/transactions/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String id) {
        transactionRepository.deleteById(id);
        return ResponseEntity.ok(String.format("Document %s removed", id));
    }


}
