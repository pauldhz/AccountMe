package org.pauldenhez.accountme.ws.transaction.controller;

import org.pauldenhez.accountme.common.model.hateoas.Links;
import org.pauldenhez.accountme.common.model.transaction.Transaction;
import org.pauldenhez.accountme.common.model.transaction.dto.TransactionDTO;
import org.pauldenhez.accountme.common.model.transaction.dto.TransactionResponse;
import org.pauldenhez.accountme.common.model.transaction.mapper.TransactionMapper;
import org.pauldenhez.accountme.ws.repository.TransactionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("")
public class TransactionController {

    private final static String TRANSACTION_BASE_URI = "/transactions";

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    private Links buildTransactionUriPagedLink(int size, int currentPageNumber, int totalPages) {
        String next = null;
        String prev = null;
        String last = null;
        String first = null;

        if(currentPageNumber < totalPages) {
            next = TRANSACTION_BASE_URI + "?size=" + size + "&page=" + (currentPageNumber + 1);
            last = TRANSACTION_BASE_URI + "?size=" + size + "&page=" + totalPages;
        }
        if(currentPageNumber > 1) {
            prev = TRANSACTION_BASE_URI + "?size=" + size + "&page=" + (currentPageNumber - 1);
            first = TRANSACTION_BASE_URI + "?size=" + size + "&page=1";
        }
        return  new Links(next, prev, last, first);
    }

    @GetMapping(path = TRANSACTION_BASE_URI)
    public ResponseEntity<TransactionResponse> listAll(@RequestParam int size, @RequestParam int page) {
        var requestedPage = transactionRepository.findAll(PageRequest.of(page,size));
        var transactions = requestedPage.stream()
                .map(TransactionMapper::toDto).toList();
        var pageLinks = buildTransactionUriPagedLink(size, page, requestedPage.getTotalPages());
        var transactionResponse = new TransactionResponse(
                transactions,
                pageLinks,
                null);
        return ResponseEntity.ok(transactionResponse);
    }

    @PutMapping(path = TRANSACTION_BASE_URI)
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) {
        Transaction savedTransaction = transactionRepository.save(TransactionMapper.fromDto(transaction));
        return ResponseEntity.ok(savedTransaction);
    }

    @PostMapping(path = TRANSACTION_BASE_URI)
    public ResponseEntity<Boolean> createTransactions(@RequestBody TransactionDTO[] transactions) {
        transactionRepository.saveAll(Arrays.stream(transactions).map(TransactionMapper::fromDto).toList());
        return ResponseEntity.ok(true);
    }

    @GetMapping(path = TRANSACTION_BASE_URI + "/{id}")
    public ResponseEntity<TransactionDTO> getById(@PathVariable String id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map((TransactionMapper::toDto))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = TRANSACTION_BASE_URI + "/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String id) {
        transactionRepository.deleteById(id);
        return ResponseEntity.ok(String.format("Document %s removed", id));
    }


}
