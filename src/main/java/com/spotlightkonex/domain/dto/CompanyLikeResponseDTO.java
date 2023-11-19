package com.spotlightkonex.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CompanyLikeResponseDTO {
    private final String corpCode;
    private final Long count;
}
