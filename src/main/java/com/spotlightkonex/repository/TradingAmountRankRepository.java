package com.spotlightkonex.repository;

import com.spotlightkonex.domain.entity.TradingAmountRank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TradingAmountRankRepository extends JpaRepository<TradingAmountRank, Long> {
    /**
     * 기업 고유번호에 해당하는 기업 랭킹 리스트(생성일 기준 내림차순 조회
     * */
    Optional<List<TradingAmountRank>> findAllByKonexStockCorpCodeOrderByCreatedAtDesc(String corpCode);
}
