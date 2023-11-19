package com.spotlightkonex.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="trading_amount_rank")
public class TradingAmountRank {
    @Id
    @Column(name="rank_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rankSeq; //거래대금 랭킹 시퀀스

    private int ranking; //랭킹

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_code")
    private KonexStock konexStock;

    @CreatedDate
    @Column(name="created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt; //생성일

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public TradingAmountRank(int ranking, KonexStock konexStock) {
        this.ranking = ranking;
        this.konexStock = konexStock;
    }
}
