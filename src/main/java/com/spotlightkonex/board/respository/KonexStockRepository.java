package com.spotlightkonex.board.respository;

import com.spotlightkonex.board.domain.entity.KonexStock;
import org.springframework.data.jpa.repository.JpaRepository;
public interface KonexStockRepository extends JpaRepository<KonexStock, Long> {
    KonexStock findByCorpCode(String cropCode);
}
