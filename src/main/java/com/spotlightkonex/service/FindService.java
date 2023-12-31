package com.spotlightkonex.service;

import org.springframework.http.ResponseEntity;

public interface FindService {
    ResponseEntity<?> getEnterpriseByKeyword(String keyword);

    ResponseEntity<?> getEnterpriseByIndutyName(Long induty_name);

    ResponseEntity<?> getEnterpriseByNominateAdviser(Long advisor);
}
