package com.spotlightkonex.controller;

import com.spotlightkonex.service.FindService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("find")
public class FindController {
    private final FindService findService;

    /**
     * 검색창 기업 검색
     * @author 이지은
     * @param keyword 검색할 키워드
     * @return 검색어에 해당하는 기업 리스트
     * */
    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<?> searchEnterpriseByKeyword(@PathVariable String keyword){
        return findService.getEnterpriseByKeyword(keyword);
    }

    /**
     * 테마별 기업 검색
     * @author 이지은
     * @param theme 검색할 테마 번호
     * @return 테마에 해당하는 종목을 가진 기업 리스트
     * */
    @GetMapping("/theme/{theme}")
    public ResponseEntity<?> searchEnterpriseByTheme(@PathVariable Long theme){
        return findService.getEnterpriseByIndutyName(theme);
    }

    /**
     * 지문자문인별 기업 검색
     * @author 이지은
     * @param advisor 검색할 지정자문인 명
     * @return 지정자문인에 해당하는 종목을 가진 기업 리스트
     * */
    @GetMapping("/advisor/{advisor}")
    public ResponseEntity<?> searchEnterpriseByAdvisor(@PathVariable String advisor){
        return findService.getEnterpriseByNominateAdviser(advisor);
    }
}
