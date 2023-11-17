package com.spotlightkonex.news.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class NewsApiResponse {

    @JsonProperty("items")
    private List<NewsApiDetailResponse> newsApiDetailResponses;

}
