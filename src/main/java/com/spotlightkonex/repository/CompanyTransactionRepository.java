package com.spotlightkonex.repository;

import com.spotlightkonex.domain.entity.KonexDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompanyTransactionRepository extends JpaRepository<KonexDetail, Long> {

    List<KonexDetail> findByKonexStockCorpCodeOrderByCreatedAtDesc(String corpCode);
}
