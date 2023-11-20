package com.spotlightkonex.repository;

import com.spotlightkonex.domain.entity.CompanyViews;
import com.spotlightkonex.domain.entity.KonexDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompanyViewsRepository extends JpaRepository<CompanyViews, Long> {

    List<CompanyViews> findByKonexStockCorpCodeOrderByCreatedAtDesc(String corpCode);
}
