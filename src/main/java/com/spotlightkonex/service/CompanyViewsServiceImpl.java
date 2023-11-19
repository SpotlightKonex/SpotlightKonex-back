package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.CompanyViewsListResponseDto;
import com.spotlightkonex.domain.entity.KonexDetail;
import com.spotlightkonex.domain.entity.CompanyViews;
import com.spotlightkonex.repository.CompanyViewsRepository;
import com.spotlightkonex.repository.KonexStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyViewsServiceImpl implements CompanyViewsService {

    private final CompanyViewsRepository companyViewsRepository;
    private final KonexStockRepository konexStockRepository;

    @Override
    public ResponseEntity<?> searchViews(String corpCode) {
        try {
            List<CompanyViews> companyViewsList = companyViewsRepository.findByKonexStockCorpCodeOrderByCreatedAtDesc(corpCode);

            if (companyViewsList == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("존재하지 않는 기업 코드 입니다." + corpCode);// 404(Not Found)
            }

            List<CompanyViewsListResponseDto> responseDtoList = new ArrayList<>();

            int limit = Math.min(companyViewsList.size(), 4);

            for (int i = 0; i < limit; i++) {
                CompanyViews companyViews = companyViewsList.get(i);

                CompanyViewsListResponseDto dto = CompanyViewsListResponseDto.builder()
                        .corpCode(companyViews.getKonexStock().getCorpCode())
                        .createdAt(companyViews.getCreatedAt())
                        .count(companyViews.getCount())
                        .build();
                responseDtoList.add(dto);
            }

            return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("조회수 조회 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
