package com.spotlightkonex.service;

import org.springframework.http.ResponseEntity;

public interface EnterpriseDetailService {
    ResponseEntity<?> getCompanyDetailByCorpCode(String corpCode);
}
