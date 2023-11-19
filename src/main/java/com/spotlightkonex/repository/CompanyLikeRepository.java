package com.spotlightkonex.repository;

import com.spotlightkonex.domain.entity.CompanyLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompanyLikeRepository extends JpaRepository<CompanyLike, Long> {

    List<CompanyLike> findByKonexStockCorpCodeOrderByCreatedAtDesc(String corpCode);

    Optional<CompanyLike> findByKonexStockCorpCodeAndCreatedAtBetween(
            String corpCode, LocalDate startOfDay, LocalDate endOfDay);
}

