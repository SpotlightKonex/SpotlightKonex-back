package com.spotlightkonex.like.domain.dto;

import com.spotlightkonex.like.domain.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LikeResponseDto {
    // 기업 코드
    private String corpCode;

    // 좋아요 인데스
    private Long likeSeq;

    // 좋아요 수
    private Long count;
}
