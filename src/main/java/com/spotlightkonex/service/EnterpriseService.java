package com.spotlightkonex.service;

import org.springframework.http.ResponseEntity;

public interface EnterpriseService {
   ResponseEntity<?> getEnterprise();
   ResponseEntity<?> getEnterpriseByRankTpye(String rankType);
}
