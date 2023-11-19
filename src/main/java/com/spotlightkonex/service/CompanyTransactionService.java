package com.spotlightkonex.service;

import org.springframework.http.ResponseEntity;

public interface CompanyTransactionService {
    ResponseEntity<?> searchTransaction(String corpCode);
}
