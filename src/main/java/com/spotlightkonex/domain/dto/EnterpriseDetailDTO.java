package com.spotlightkonex.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class EnterpriseDetailDTO {
    private final String corName; //기업명
    private final String indutyName; //업종명
    private final String establish_date; //설립일
    private final String public_date; //상장일
    private final Long capital; // 자본금
    private final String address; // 주소
    private final String url; // 홈페이지
}
