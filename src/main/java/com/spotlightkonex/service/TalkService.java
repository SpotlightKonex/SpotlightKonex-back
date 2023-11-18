package com.spotlightkonex.service;

import org.springframework.http.ResponseEntity;

public interface TalkService {
    ResponseEntity<?> getCompanyTalkByCorpCode(String corpCode);
}
