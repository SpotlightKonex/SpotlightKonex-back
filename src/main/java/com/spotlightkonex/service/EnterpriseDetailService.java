package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.EnterpriseDetailRequestDto;
import org.springframework.http.ResponseEntity;

public interface EnterpriseDetailService {
    ResponseEntity<?> getCompanyDetailByCorpCode(String corpCode);

    ResponseEntity<?> modifyCompanyDescription(EnterpriseDetailRequestDto enterpriseDetailRequestDto);
}
