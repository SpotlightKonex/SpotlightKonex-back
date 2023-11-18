package com.spotlightkonex.enterprise.service;

import org.springframework.http.ResponseEntity;

public interface EnterpriseService {
   ResponseEntity<?> getEnterprise();
   ResponseEntity<?> getEnterpriseByRankTpye(String rankType);
}
