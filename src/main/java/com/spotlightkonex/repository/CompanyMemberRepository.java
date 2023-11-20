package com.spotlightkonex.repository;

import com.spotlightkonex.domain.entity.CompanyMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyMemberRepository extends JpaRepository<CompanyMember, Long> {
    /**
     * 기업 관리자 이메일에 해당하는 기업 코드 조회
     * */
    Optional<CompanyMember> findByEmail(String email);

    /**
     * 기업 담당자 이메일 조회
     * */
    Optional<CompanyMember> findByKonexStockCorpCode(String corpCode);

    Optional<List<CompanyMember>> findByCorpAuth(boolean corpAuth);
}
