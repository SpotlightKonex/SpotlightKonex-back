package com.spotlightkonex.repository;

import com.spotlightkonex.domain.entity.CompanyLike;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompanyLikeRepository extends JpaRepository<CompanyLike, Long> {

    List<CompanyLike> findByKonexStockCorpCodeOrderByCreatedAtDesc(String corpCode);
}

