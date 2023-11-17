package com.spotlightkonex.enterprise.domain.entity;

import com.spotlightkonex.find.domain.entity.KonexStock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="konex_detail")
@EntityListeners(AuditingEntityListener.class)
public class KonexDetail {
    @Id
    @Column(name="detail_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailSeq; //코넥스 디테일 인덱스

    private Long price; //시세

    @Column(name="cmpprevdd_prc")
    private String cmpprevddPrc; //전일대비지수

    @Column(name="prev_price")
    private Long prevPrice; //전일종가

    @Column(name="trading_volume")
    private String tradingVolume; //거래량

    @Column(name="transaction_amount")
    private Long transactionAmount; //거래대금

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_code") //기업 고유 번호
    private KonexStock konexStock;

    @LastModifiedDate
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime modifiedAt; //수정일

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt; //생성일

}
