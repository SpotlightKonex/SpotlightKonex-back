package com.spotlightkonex.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class NewsApiResponseDTO {

    @JsonProperty("items")
    private List<NewsApiDetailResponseDTO> newsApiDetailResponsDTOS;

}
