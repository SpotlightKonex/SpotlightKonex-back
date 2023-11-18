package com.spotlightkonex.repository;

import com.spotlightkonex.domain.dto.EnterpriseDTO;
import com.spotlightkonex.domain.dto.TopResponseDTO;
import com.spotlightkonex.domain.entity.KonexDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnterpriseRepository extends JpaRepository<KonexDetail, Long> {
    /**
     * 모든 코넥스 기업 조회
     * */
    @Query(value = "SELECT d.konexStock.corpCode, s.corpName, s.logo, d.price, d.prevPrice" +
            " FROM KonexDetail d LEFT JOIN KonexStock s ON d.konexStock.corpCode = s.corpCode")
    Optional<List<EnterpriseDTO>> getAllEnterprise();

    /**
     * 거래대금 top 11 조회
     * */
//    @Query(value = "SELECT d.konexStock.corpCode, s.corpCode, s.logo, d.price, d.cmpprevddPrc FROM ( " +
//            "SELECT d.konexStock.corpCode, s.corpCode, s.logo, d.price, d.cmpprevddPrc, ROW_NUMBER() OVER (ORDER BY d.transaction_amount) " +
//            "FROM KonexDetail d LEFT JOIN KonexStock s ON d.konexStock.corpCode = s.corpCode)")
//    Optional<List<EnterpriseTopDto>> getTop11ByCmpprevddPrc();
    @Query(value = "SELECT d.corp_code, s.corp_code, s.logo, d.price, d.cmpprevdd_prc " +
            "FROM konex_detail d LEFT JOIN konex_stock s ON d.corp_code = s.corp_code " +
            "ORDER BY d.transaction_amount DESC LIMIT 11", nativeQuery = true)
    Optional<List<TopResponseDTO>> getTop11ByTransactionAmount();

    /**
     * 좋아요수 top 11 조회
     * */
    @Query(value = "SELECT d.corp_code, s.corp_code, s.logo, d.price, d.cmpprevdd_prc " +
            "FROM konex_detail d LEFT JOIN konex_stock s ON d.corp_code = s.corp_code " +
            "AND d.corp_code = (SELECT sub.corp_code FROM ( " +
            "SELECT l.corp_code as corpCode, SUM(l.count) as likeCount " +
            "FROM company_like l GROUP BY l.corp_code ORDER BY likeCount DESC LIMIT 11) sub)", nativeQuery = true)
    Optional<List<TopResponseDTO>> getTop11ByLike();

    /**
     * 조회수 top 11 조회
     * */
    @Query(value = "SELECT d.corp_code, s.corp_code, s.logo, d.price, d.cmpprevdd_prc " +
            "FROM konex_detail d LEFT JOIN konex_stock s ON d.corp_code = s.corp_code " +
            "AND d.corp_code = (SELECT sub.corp_code FROM ( " +
            "SELECT v.corp_code as corpCode, SUM(v.count) as viewsCount " +
            "FROM company_views v GROUP BY v.corp_code ORDER BY viewsCount DESC LIMIT 11) sub)", nativeQuery = true)
    Optional<List<TopResponseDTO>> getTop11ByViews();
}
