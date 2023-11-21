package com.spotlightkonex.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EnterpriseDetailRequestDto {
    private String corpCode; //기업 고유번호
    private String description; // 기업 소개
}
