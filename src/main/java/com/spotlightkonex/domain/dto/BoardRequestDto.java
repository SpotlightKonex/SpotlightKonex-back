package com.spotlightkonex.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardRequestDto {
    private String title;

    private String context;

    private String corpCode;

}
