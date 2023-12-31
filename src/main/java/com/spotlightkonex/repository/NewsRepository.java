package com.spotlightkonex.repository;

import com.spotlightkonex.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query(value = "SELECT * FROM news WHERE title LIKE %:cropName% AND pub_date LIKE '%2023%' ORDER BY RAND() LIMIT 2", nativeQuery = true)
    Optional<List<News>> findRandomNewsByCropName(@Param("cropName") String cropName);

}