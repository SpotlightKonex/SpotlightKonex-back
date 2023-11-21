package com.spotlightkonex.repository;

import com.spotlightkonex.domain.dto.EnterpriseResponseDTO;
import com.spotlightkonex.domain.entity.KonexDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KonexDetailRepository extends JpaRepository<KonexDetail, Long> {
    /**
     * 모든 코넥스 기업 조회
     * */
    @Query(value = "SELECT s.corp_code as corpCode, s.corp_name as corpName, s.logo as logo, d.price as price, d.cmpprevdd_prc as cmpprevddPrc " +
            "FROM konex_stock s LEFT JOIN konex_detail d ON d.corp_code = s.corp_code " +
            "ORDER BY d.modified_at DESC, s.corp_name LIMIT 129 ", nativeQuery = true)
    Optional<List<EnterpriseResponseDTO>> getAllEnterprise();

    /**
     * 거래대금 top 11 조회
     * */
    @Query(value = "SELECT corpCode, corpName, logo, price, cmpprevddPrc " +
            "FROM (SELECT s.corp_code as corpCode, s.corp_name as corpName, s.logo as logo, d.price as price, d.cmpprevdd_prc as cmpprevddPrc, d.trading_amount as trdingAmount " +
            "FROM konex_stock s LEFT JOIN konex_detail d ON d.corp_code = s.corp_code ORDER BY d.modified_at DESC LIMIT 129) sub " +
            "ORDER BY trdingAmount DESC LIMIT 11", nativeQuery = true)
    Optional<List<EnterpriseResponseDTO>> getTop11ByTransactionAmount();

    /**
     * 좋아요수 top 11 조회
     * */
    @Query(value = "SELECT corpCode, corpName, logo, price, cmpprevddPrc " +
            "FROM (SELECT s.corp_code as corpCode, s.corp_name as corpName, s.logo, d.price, d.cmpprevdd_prc as cmpprevddPrc FROM konex_stock s LEFT JOIN konex_detail d ON d.corp_code = s.corp_code ORDER BY d.modified_at DESC LIMIT 129) s " +
            "WHERE corpCode IN (SELECT sub.corpCode FROM (SELECT l.corp_code as corpCode, SUM(l.count) as likeCount " +
            "FROM company_like l GROUP BY l.corp_code ORDER BY likeCount DESC LIMIT 11) sub)", nativeQuery = true)
    Optional<List<EnterpriseResponseDTO>> getTop11ByLike();

    /**
     * 조회수 top 11 조회
     * */
    @Query(value = "SELECT corpCode, corpName, logo, price, cmpprevddPrc " +
            "FROM (SELECT s.corp_code as corpCode, s.corp_name as corpName, s.logo, d.price, d.cmpprevdd_prc as cmpprevddPrc FROM konex_stock s LEFT JOIN konex_detail d ON d.corp_code = s.corp_code ORDER BY d.modified_at DESC LIMIT 129) s " +
            "WHERE corpCode IN (SELECT sub.corpCode FROM (SELECT v.corp_code as corpCode, SUM(v.count) as viewsCount " +
            "FROM company_views v GROUP BY v.corp_code ORDER BY viewsCount DESC LIMIT 11) sub)", nativeQuery = true)
    Optional<List<EnterpriseResponseDTO>> getTop11ByViews();

    /**
     * 해당 일자 기준 거래대금 순 기업 상세 리스트
     * */
    @Query(value = "SELECT d FROM KonexDetail d WHERE DATE_FORMAT(d.createdAt, '%Y-%m-%d') = :createAt ORDER BY d.tradingAmount DESC")
    Optional<List<KonexDetail>> findAllByCreatedAtOrderByTradingAmountDesc(@Param("createAt") String createAt);
}
