package com.spotlightkonex.repository;

import com.spotlightkonex.domain.entity.KonexStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KonexStockRepository extends JpaRepository<KonexStock, Long> {
    Optional<KonexStock> findByCorpCode(String cropCode);

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

}
