package com.spotlightkonex.like.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface LikeService {
    ResponseEntity<?> searchLike(String corpCode);
}
