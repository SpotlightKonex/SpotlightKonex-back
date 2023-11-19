package com.spotlightkonex.controller;

import com.spotlightkonex.domain.dto.TalkRequestDTO;
import com.spotlightkonex.service.EnterpriseDetailService;
import com.spotlightkonex.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enterprise")
public class EnterpriseDetailController {
    private final TalkService talkService;
    private final EnterpriseDetailService detailService;

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
        return detailService.getCompanyDetailByCorpCode(corpCode);
    }
}