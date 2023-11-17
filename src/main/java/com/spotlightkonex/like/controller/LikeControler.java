package com.spotlightkonex.like.controller;

import com.spotlightkonex.like.service.LikeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class LikeControler {
    private final LikeServiceImpl likeService;

    @GetMapping("/analysis/like/{crop_code}")
    public ResponseEntity<?> searchLike(@PathVariable String crop_code) {
        return likeService.searchLike(crop_code);
    }

}
