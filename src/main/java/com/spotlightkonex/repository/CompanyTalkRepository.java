package com.spotlightkonex.repository;

import com.spotlightkonex.domain.entity.CompanyTalk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyTalkRepository extends JpaRepository<CompanyTalk, Long> {
    /**
     * 최신순 해당 기업의 채팅 리스트
     * */
    Optional<List<CompanyTalk>> findAllByKonexStockCorpCodeOrderByCreatedAtDesc(String corpCode);

    /**
     * 최신순 해당 기업 채팅 모아보기
     */
    Optional<List<CompanyTalk>> findAllByKonexStockCorpCodeAndWriterTypeOrderByCreatedAt(String corpCode, int writeType);
}
