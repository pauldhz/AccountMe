package org.pauldenhez.accountme.controller;

import org.pauldenhez.accountme.service.ElasticReaderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ElasticReaderController {

    private final ElasticReaderService elasticReaderService;

    public ElasticReaderController(ElasticReaderService elasticReaderService) {
        this.elasticReaderService = elasticReaderService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getInfo() throws IOException {
        return ResponseEntity.ok().body(this.elasticReaderService.read().toString());
    }
}
