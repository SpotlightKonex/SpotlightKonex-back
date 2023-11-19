package com.spotlightkonex.controller;

import com.spotlightkonex.domain.dto.NewsResponseDTO;
import com.spotlightkonex.service.NewsService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<NewsResponseDTO>> getNews(
            @RequestParam String companyName
    ) {
        return ResponseEntity.ok(newsService.getNews(companyName));
    }
}
