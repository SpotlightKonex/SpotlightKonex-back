package com.spotlightkonex.repository;

import com.spotlightkonex.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query(value = "SELECT n FROM News n WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :companyName, '%')) ORDER BY RAND() LIMIT 4")
    Optional<List<News>> getNewsByCompanyName(String companyName);

}
