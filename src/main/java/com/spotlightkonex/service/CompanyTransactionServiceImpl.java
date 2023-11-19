package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.CompanyTransactionListResponseDto;
import com.spotlightkonex.domain.entity.KonexDetail;
import com.spotlightkonex.repository.CompanyTransactionRepository;
import com.spotlightkonex.repository.KonexStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyTransactionServiceImpl implements CompanyTransactionService {

    private final CompanyTransactionRepository companyTransactionRepository;
    private final KonexStockRepository konexStockRepository;

    @Override
    public ResponseEntity<?> searchTransaction(String corpCode) {
        try {
            List<KonexDetail> companyTransactionList = companyTransactionRepository.findByKonexStockCorpCodeOrderByCreatedAtDesc(corpCode);

            if (companyTransactionList == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("존재하지 않는 기업 코드 입니다." + corpCode);// 404(Not Found)
            }

            List<CompanyTransactionListResponseDto> responseDtoList = new ArrayList<>();

            int limit = Math.min(companyTransactionList.size(), 4);

            for (int i = 0; i < limit; i++) {
                KonexDetail companyTransaction = companyTransactionList.get(i);

                CompanyTransactionListResponseDto dto = CompanyTransactionListResponseDto.builder()
                        .corpCode(companyTransaction.getKonexStock().getCorpCode())
                        .createdAt(companyTransaction.getCreatedAt())
                        .tradingVolume(companyTransaction.getTradingVolume())
                        .build();
                responseDtoList.add(dto);
            }

            return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("거래량 수 조회 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
