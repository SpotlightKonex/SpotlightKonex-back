package com.spotlightkonex.like.repository;

import com.spotlightkonex.like.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Like findByKonexStockCorpCode(String corpCode);
}
