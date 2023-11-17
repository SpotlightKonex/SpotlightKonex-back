package com.spotlightkonex.enterprise.repository;

import com.spotlightkonex.enterprise.controller.dto.EnterpriseDto;
import com.spotlightkonex.enterprise.domain.KonexDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnterpriseRepository extends JpaRepository<KonexDetail, Long> {
    @Query(value = "SELECT d.konexStock.corpCode, s.corpName, s.logo, d.price, d.prevPrice" +
            " FROM KonexDetail d LEFT JOIN KonexStock s ON d.konexStock.corpCode = s.corpCode")
    Optional<List<EnterpriseDto>> getAllEnterprise();
}
