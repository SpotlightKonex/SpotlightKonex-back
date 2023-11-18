package com.spotlightkonex.enterprise.domain.entity;

import com.spotlightkonex.find.domain.entity.KonexStock;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="company_like")
@EntityListeners(AuditingEntityListener.class)
public class CompanyLike {
    @Id
    @Column(name = "like_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeSeq;

    @Column(name = "count")
    private Long count; //좋아요수

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