package com.spotlightkonex.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BalanceSheetResponseDto {
    private String corpCode; // 기업 고유 번

    private float div; // 배당 수익률

    private float bps; // 주당순자산가치

    private float per; // 주가수익비율

    private float eps; // 주당순이익

    private float pbr; // 주가순자산비율
}
