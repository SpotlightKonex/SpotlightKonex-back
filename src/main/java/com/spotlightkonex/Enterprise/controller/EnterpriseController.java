package com.spotlightkonex.Enterprise.controller;

import com.spotlightkonex.Enterprise.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class EnterpriseController {
    private final EnterpriseService enterpriseServie;

    /**
     * 모든 코넥스 기업 조회
     * @return 모든 코넥스 기업 리스트
     * */
    @GetMapping("/enterprise")
    public ResponseEntity<?> searchAll(){
        return enterpriseServie.getEnterprise();
    }
}
