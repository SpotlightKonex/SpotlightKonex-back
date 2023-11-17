package com.spotlightkonex.like.service;

import com.spotlightkonex.like.domain.dto.LikeResponseDto;
import com.spotlightkonex.like.domain.entity.Like;
import com.spotlightkonex.like.domain.entity.KonexStock;
import com.spotlightkonex.like.repository.KonexStockRepository;
import com.spotlightkonex.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final KonexStockRepository konexStockRepository;

    @Override
    public ResponseEntity<?> searchLike(String corpCode) {
        try {
            Like like = likeRepository.findByKonexStockCorpCode(corpCode);

            if (like == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("존재하지 않는 기업 코드 입니다." + corpCode);// 404(Not Found)
            }

            LikeResponseDto dto = LikeResponseDto.builder()
                    .corpCode(like.getKonexStock().getCorpCode())
                    .likeSeq(like.getLikeSeq())
                    .count(like.getCount())
                    .build();

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("좋아요 수 조회 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


