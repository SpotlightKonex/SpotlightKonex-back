package com.spotlightkonex.controller;

import com.spotlightkonex.domain.dto.BalanceSheetResponseDto;
import com.spotlightkonex.service.BalanceSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/balance")
public class BalanceSheetController {
    private final BalanceSheetService balanceSheetService;

    @GetMapping("/{corpCode}")
    public ResponseEntity<BalanceSheetResponseDto> getBalanceSheetByCorpCode(@RequestParam String corpCode) {
        return ResponseEntity.ok().body(balanceSheetService.getBalanceSheetByCorpCode(corpCode));
    }
}
