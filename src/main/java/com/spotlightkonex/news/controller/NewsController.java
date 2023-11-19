package com.spotlightkonex.news.controller;

import com.spotlightkonex.news.controller.dto.NewsResponse;
import com.spotlightkonex.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/v1/news")
    public ResponseEntity<List<NewsResponse>> getNews() {
        return ResponseEntity.ok(newsService.getNews());
    }
}
