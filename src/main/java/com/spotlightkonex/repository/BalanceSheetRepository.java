package com.spotlightkonex.repository;

import com.spotlightkonex.domain.entity.BalanceSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceSheetRepository extends JpaRepository<BalanceSheet, Long> {
}
