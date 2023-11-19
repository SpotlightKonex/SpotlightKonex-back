package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.TalkRequestDTO;
import org.springframework.http.ResponseEntity;

public interface TalkService {
    ResponseEntity<?> getCompanyTalkByCorpCode(String corpCode, boolean status);

    ResponseEntity<?> writeCompanyTalkByCorpCode(TalkRequestDTO talkRequestDTO);
}
