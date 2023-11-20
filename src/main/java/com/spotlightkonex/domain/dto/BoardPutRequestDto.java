package com.spotlightkonex.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class BoardPutRequestDto {
    private String corp_code;

    private String title;

    private String context;

    private Long noticeSeq;
}
