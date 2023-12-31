package com.spotlightkonex.service;

import org.springframework.http.ResponseEntity;

public interface CompanyLikeService {
    ResponseEntity<?> searchLike(String corpCode);

    ResponseEntity<?> getTotalCompanyLike(String corpCode);

    ResponseEntity<?> postCompanyLike(String corpCode);
}
