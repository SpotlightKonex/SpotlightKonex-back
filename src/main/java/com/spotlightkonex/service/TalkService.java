package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.TalkRequestDTO;
import com.spotlightkonex.security.CompanyMemberDetails;
import org.springframework.http.ResponseEntity;

public interface TalkService {
    ResponseEntity<?> getCompanyTalkByCorpCode(String corpCode, boolean status);

    ResponseEntity<?> writeCompanyTalkByCorpCode(CompanyMemberDetails companyMemberDetails, TalkRequestDTO talkRequestDTO);
}
