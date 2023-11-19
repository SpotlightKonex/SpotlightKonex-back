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
@Table(name ="company_talk")
@EntityListeners(AuditingEntityListener.class)
public class CompanyTalk {
    @Id
    @Column(name = "talk_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long talkSeq;

    private String context; //채팅 내용

    @Column(name = "writer_type")
    private int writerType; //작성자 구분

    private String nickname; //닉네임

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
