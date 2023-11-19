package com.spotlightkonex.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NewsApiDetailResponseDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("link")
    private String link;

    @JsonProperty("description")
    private String description;

    @JsonProperty("pubDate")
    private String pubDate;

}
