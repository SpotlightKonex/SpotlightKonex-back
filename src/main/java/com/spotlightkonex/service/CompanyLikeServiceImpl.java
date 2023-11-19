package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.CompanyLikeListResponseDto;
import com.spotlightkonex.domain.dto.CompanyLikeResponseDTO;
import com.spotlightkonex.domain.entity.CompanyLike;
import com.spotlightkonex.domain.entity.KonexStock;
import com.spotlightkonex.repository.CompanyLikeRepository;
import com.spotlightkonex.repository.KonexStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyLikeServiceImpl implements CompanyLikeService {

    private final CompanyLikeRepository companyLikeRepository;
    private final KonexStockRepository konexStockRepository;

    @Override
    public ResponseEntity<?> searchLike(String corpCode) {
        try {
            List<CompanyLike> companyLikeList = companyLikeRepository.findByKonexStockCorpCodeOrderByCreatedAtDesc(corpCode);

            if (companyLikeList == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("존재하지 않는 기업 코드 입니다." + corpCode);// 404(Not Found)
            }

            List<CompanyLikeListResponseDto> responseDtoList = new ArrayList<>();

            int limit = Math.min(companyLikeList.size(), 4);

            for (int i = 0; i < limit; i++) {
                CompanyLike companyLike = companyLikeList.get(i);

                CompanyLikeListResponseDto dto = CompanyLikeListResponseDto.builder()
                        .corpCode(companyLike.getKonexStock().getCorpCode())
                        .createdAt(companyLike.getCreatedAt())
                        .count(companyLike.getCount())
                        .build();
                responseDtoList.add(dto);
            }

            return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("좋아요 수 조회 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getTotalCompanyLike(String cropCode) {
        try {

            konexStockRepository.findByCorpCode(cropCode)
                    .orElseThrow(() -> new NullPointerException("잘못된 기업코드입니다."));

            Long totalCompanyLike;
            List<CompanyLike> companyLikeList = companyLikeRepository.findByKonexStockCorpCodeOrderByCreatedAtDesc(cropCode);

            if (companyLikeList == null) {
                totalCompanyLike = 0L;
            } else {
                totalCompanyLike = companyLikeList.stream()
                        .mapToLong(CompanyLike::getCount)
                        .sum();
            }

            CompanyLikeResponseDTO dto = CompanyLikeResponseDTO
                    .builder()
                    .corpCode(cropCode)
                    .count(totalCompanyLike)
                    .build();

            return new ResponseEntity<>(dto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("좋아요 수 조회 실패", HttpStatus.NOT_FOUND);
        }
    }

//    @Override
//    public ResponseEntity<?> postCompanyLike(String corpCode) {
//
//    }
}
