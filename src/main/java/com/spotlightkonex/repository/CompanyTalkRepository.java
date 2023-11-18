package com.spotlightkonex.repository;

import com.spotlightkonex.domain.entity.CompanyTalk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyTalkRepository extends JpaRepository<CompanyTalk, Long> {
    Optional<List<CompanyTalk>> findAllByKonexStockCorpCodeOrderByCreatedAtDesc(String corpCode);
}
