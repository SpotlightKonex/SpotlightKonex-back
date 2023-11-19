package com.spotlightkonex.controller;

import com.spotlightkonex.service.CompanyViewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CompanyViewsController {
    private final CompanyViewsServiceImpl CompanyViewsService;

    @GetMapping("/analysis/views/{crop_code}")
    public ResponseEntity<?> searchViews(@PathVariable String crop_code) {
        return CompanyViewsService.searchViews(crop_code);
    }
}
