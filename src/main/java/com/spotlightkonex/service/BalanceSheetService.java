package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.BalanceSheetResponseDto;
import com.spotlightkonex.domain.entity.BalanceSheet;
import com.spotlightkonex.exception.CustomException;
import com.spotlightkonex.exception.ErrorCode;
import com.spotlightkonex.repository.BalanceSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceSheetService {
    private final BalanceSheetRepository balanceSheetRepository;

    public BalanceSheetResponseDto getBalanceSheetByCorpCode(String corpCode) {
        BalanceSheet balanceSheet = balanceSheetRepository.findByKonexStock_CorpCode(corpCode)
                .orElseThrow(() -> new CustomException(ErrorCode.COMPANY_NOT_FOUND));

        return BalanceSheetResponseDto.builder()
                .corpCode(balanceSheet.getKonexStock().getCorpCode())
                .div(balanceSheet.getDiv())
                .bps(balanceSheet.getBps())
                .per(balanceSheet.getPer())
                .eps(balanceSheet.getEps())
                .pbr(balanceSheet.getPbr())
                .build();
    }


}
