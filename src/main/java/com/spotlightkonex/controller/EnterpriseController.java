package com.spotlightkonex.controller;

import com.spotlightkonex.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class EnterpriseController {
    private final EnterpriseService enterpriseServie;

    /**
     * 모든 코넥스 기업 조회
     * @return 모든 기입 코넥스 기업 리스트
     * */
    @GetMapping("/enterprise")
    public ResponseEntity<?> searchAll(){
        return enterpriseServie.getEnterprise();
    }

    /**
     * 거래대금, 좋아요, 조회수 top 11 조회
     * @param rankType 랭크 타입
     * @return 랭크 타입에 해당하는 top 11 기업 리스트
     * */
    @GetMapping("/top/{rankType}")
    public ResponseEntity<?> searchEnterpriseByRankType(@PathVariable String rankType){
        return enterpriseServie.getEnterpriseByRankTpye(rankType);
    }
}
