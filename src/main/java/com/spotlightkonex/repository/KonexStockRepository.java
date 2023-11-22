package com.spotlightkonex.repository;

import com.spotlightkonex.domain.dto.EnterpriseResponseDTO;
import com.spotlightkonex.domain.entity.KonexStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KonexStockRepository extends JpaRepository<KonexStock, Long> {
    Optional<KonexStock> findByCorpCode(String corpCode);

    Optional<KonexStock> findByCorpName(String corpName);

    /**
     * 키워드가 들어간 종목 코드에 해당하는 기업 조회
     * */
    List<KonexStock> findAllByStockCodeContaining(String keyword);

    /**
     * 키워드가 들어간 기업명에 해당하는 기업 조회
     * */
    List<KonexStock> findAllByCorpNameContaining(String keyword);

    /**
     * 해당 업종(테마)이 들어간 기업 조회
     * */
    List<KonexStock> findAllByIndutyNameContaining(String indutyName);

    /**
     * 요청한 지정자문인이 관리하는 기업 조회
     * */
    List<KonexStock> findByNominateAdviserContaining(String nominateAdviser);

    /**
     * 가업 고유번호에 따른 최신 시세 정보 조회
     * */
    @Query(value = "SELECT s.corp_code as corpCode, s.corp_name as corpName, s.logo as logo, d.price as price, d.cmpprevdd_prc as cmpprevddPrc " +
            "FROM konex_stock s LEFT JOIN konex_detail d ON d.corp_code = s.corp_code AND d.corp_code = ?1 " +
            "ORDER BY d.modified_at DESC LIMIT 1", nativeQuery = true)
    EnterpriseResponseDTO findDetailByCorpCode(String corpCode);
}
