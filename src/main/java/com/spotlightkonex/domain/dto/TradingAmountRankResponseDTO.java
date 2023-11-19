package com.spotlightkonex.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class TradingAmountRankResponseDTO {
    public final  int ranking; //랭킹 순위
    public final String day; //기준 일
}
