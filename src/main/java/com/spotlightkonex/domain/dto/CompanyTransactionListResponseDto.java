package com.spotlightkonex.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CompanyTransactionListResponseDto {
    // 기업 코드
    private String corpCode;

    // 생성 날짜
    private LocalDateTime createdAt;

    // 거래량 수
    private String tradingVolume;
}
