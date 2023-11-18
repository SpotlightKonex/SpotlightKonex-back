package com.spotlightkonex.controller;

import com.spotlightkonex.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enterprise")
public class EnterpriseDetailController {
    private final TalkService talkService;

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
     * 기업 댓글 생성
     * */
}