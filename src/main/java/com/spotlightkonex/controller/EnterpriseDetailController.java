package com.spotlightkonex.controller;

import com.spotlightkonex.domain.dto.TalkRequestDTO;
import com.spotlightkonex.service.EnterpriseDetailService;
import com.spotlightkonex.service.TalkService;
import com.spotlightkonex.service.TradingAmountRankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enterprise")
public class EnterpriseDetailController {
    private final TalkService talkService;
    private final EnterpriseDetailService enterpriseDetailService;
    private final TradingAmountRankService tradingAmountRankService;

    /**
     * 최신순 기업 댓글 조회
     * @param corpCode 기업 고유 코드
     * @return 해당 기업 댓글 리스트
     * */
    @GetMapping("/talk")
    public ResponseEntity<?> getCompanyTalkByCorpCode(@RequestParam String corpCode, boolean status){
        return talkService.getCompanyTalkByCorpCode(corpCode, status);
    }

    /**
     * 기업 댓글 작성
     * @param talkRequestDTO 작성한 댓글 정보 및 작성자
     * @return 완료 여부
     * */
    @PostMapping("/talk")
    public ResponseEntity<?> writeTalkByCorpCode(@RequestBody TalkRequestDTO talkRequestDTO){
        return talkService.writeCompanyTalkByCorpCode(talkRequestDTO);
    }

    /**
     * 기업 상세 조회
     * @param corpCode 조회할 기업 고유코드
     * @return 기업 상세 정보
     * */
    @GetMapping("/{corpCode}")
    public ResponseEntity<?> getCompanyDetail(@PathVariable String corpCode){
        return enterpriseDetailService.getCompanyDetailByCorpCode(corpCode);
    }

    /**
     * 거래대금 기준 순위변동 조회(4차 ~ 1차 순)
     * @param corpCode 조회할 기업 고유코드
     * @return 기업 순위 리스트(1차 ~ 4차)
     * */
    @GetMapping("/rank/{corpCode}")
    public ResponseEntity<?> getTradingAmountRank(@PathVariable String corpCode){
        return tradingAmountRankService.getTradingAmountRankByCorpCode(corpCode);
    }
}