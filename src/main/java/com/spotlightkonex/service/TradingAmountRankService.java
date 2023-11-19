package com.spotlightkonex.service;

import org.springframework.http.ResponseEntity;

public interface TradingAmountRankService {
    ResponseEntity<?> getTradingAmountRankByCorpCode(String corpCode);
}
