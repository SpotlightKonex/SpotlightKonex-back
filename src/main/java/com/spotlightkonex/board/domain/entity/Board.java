package com.spotlightkonex.board.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="board")
public class Board {
    /**
     * 기업 코드
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "notice_seq", nullable = false)
    private Long noticeSeq;

    /**
     * 게시글 제목
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * 게시글 내용
     */
    @Column(name = "context", nullable = false)
    private String context;

    /**
     * 기업 코드(코넥스 표에서 corp_code가져오기)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_code")
    private KonexStock konexStock;

    @LastModifiedDate
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime modifiedAt; //수정일

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt; //생성일

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }

}