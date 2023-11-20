package com.spotlightkonex.controller;

import com.spotlightkonex.service.CompanyViewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CompanyViewsController {
    private final CompanyViewsServiceImpl CompanyViewsService;

    @GetMapping("/analysis/views/{corp_code}")
    public ResponseEntity<?> searchViews(@PathVariable String corp_code) {
        return CompanyViewsService.searchViews(corp_code);
    }
}
