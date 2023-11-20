package com.spotlightkonex.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="company_views")
@EntityListeners(AuditingEntityListener.class)
public class CompanyViews {
    @Id
    @Column(name = "views_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long viewsSeq;

    @Column(name = "count")
    private Long count; //조회수

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