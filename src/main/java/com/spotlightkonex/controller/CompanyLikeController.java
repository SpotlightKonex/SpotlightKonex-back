package com.spotlightkonex.controller;

import com.spotlightkonex.domain.entity.CompanyNews;
import com.spotlightkonex.service.CompanyLikeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CompanyLikeController {
    private final CompanyLikeServiceImpl CompanyLikeService;

    @GetMapping("/analysis/like/{crop_code}")
    public ResponseEntity<?> searchLike(@PathVariable String crop_code) {
        return CompanyLikeService.searchLike(crop_code);
    }

    @GetMapping("/enterprise/like")
    public ResponseEntity<?> getTotalCompanyLike(
            @RequestParam String corpCode
    ) {
        return CompanyLikeService.getTotalCompanyLike(corpCode);
    }

}