package com.spotlightkonex.repository;

import com.spotlightkonex.domain.entity.BalanceSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceSheetRepository extends JpaRepository<BalanceSheet, Long> {
    Optional<BalanceSheet> findByKonexStock_CorpCode(String corpCode);
}
