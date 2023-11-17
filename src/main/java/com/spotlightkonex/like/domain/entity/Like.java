package com.spotlightkonex.like.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="like")
public class Like {
    /**
     * 좋아요 인덱스
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "like_seq", nullable = false)
    private Long likeSeq;

    /**
     * 좋아요 수
     */
    @Column(name = "count", nullable = false)
    private Long count;


    /**
     * 기업 코드(코넥스 표에서 corp_code가져오기)
     */
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
