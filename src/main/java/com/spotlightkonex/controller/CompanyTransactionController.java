package com.spotlightkonex.controller;

import com.spotlightkonex.service.CompanyTransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CompanyTransactionController {
    private final CompanyTransactionServiceImpl CompanyTransactionService;

    @GetMapping("/analysis/transaction/{crop_code}")
    public ResponseEntity<?> searchTransaction(@PathVariable String crop_code) {
        return CompanyTransactionService.searchTransaction(crop_code);
    }

}

