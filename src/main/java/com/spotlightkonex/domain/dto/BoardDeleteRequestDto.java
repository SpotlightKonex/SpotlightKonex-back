package com.spotlightkonex.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class BoardDeleteRequestDto {
    private Long noticeSeq;

    private String corpCode;
}
