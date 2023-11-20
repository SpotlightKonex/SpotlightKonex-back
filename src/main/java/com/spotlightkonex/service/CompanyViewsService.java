package com.spotlightkonex.service;

import org.springframework.http.ResponseEntity;

public interface CompanyViewsService {
    ResponseEntity<?> searchViews(String cropCode);
}
