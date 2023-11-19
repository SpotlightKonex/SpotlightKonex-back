package com.spotlightkonex.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="company_news")
@EntityListeners(AuditingEntityListener.class)
public class CompanyNews {
    @Id
    @Column(name = "news_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsSeq;

    private String title; //뉴스 제목

    private String url; //뉴스 url

    private String description; //뉴스 요약

    @Column(name="pub_date")
    private String pubDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_code")
    private KonexStock konexStock;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt; //생성일

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
