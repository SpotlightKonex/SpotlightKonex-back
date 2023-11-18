package com.spotlightkonex.controller;

import com.spotlightkonex.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/talk/{corpCode}")
    public ResponseEntity<?> searchTalkByCorpCode(@PathVariable String corpCode){
        return talkService.getCompanyTalkByCorpCode(corpCode);
    }

    /**
     * 기업 댓글 생성
     * */

    /**
     * 해당 기업 댓글 모아보기
     * */
}