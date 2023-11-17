package com.spotlightkonex.like.repository;

import com.spotlightkonex.like.domain.entity.KonexStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KonexStockRepository extends JpaRepository<KonexStock, Long> {
}
