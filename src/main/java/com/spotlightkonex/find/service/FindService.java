package com.spotlightkonex.find.service;

import org.springframework.http.ResponseEntity;

public interface FindService {
    ResponseEntity<?> getEnterpriseByKeyword(String keyword);

    ResponseEntity<?> getEnterpriseByIndutyName(Long induty_name);

    ResponseEntity<?> getEnterpriseByNominateAdviser(String advisor);
}
