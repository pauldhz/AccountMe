package org.pauldenhez.accountme.ws.controller;

import org.pauldenhez.accountme.ws.repository.TransactionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("dev")
@RestController
@RequestMapping("/dev-only")
public class DevOnlyController {

    private final TransactionRepository transactionRepository;

    public DevOnlyController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @DeleteMapping(path = "/transactions")
    public ResponseEntity<Boolean> deleteTransactions() {
        transactionRepository.deleteAll();
        return ResponseEntity.ok(true);
    }
}
