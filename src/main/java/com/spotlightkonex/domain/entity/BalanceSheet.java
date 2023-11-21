package com.spotlightkonex.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "balance_sheet")
public class BalanceSheet {
    @Id
    @Column(name="balance_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long balanceSeq; //코넥스 디테일 인덱스

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_code") //기업 고유 번호
    private KonexStock konexStock;

    private float div; // 배당 수익률

    private float bps; // 주당순자산가치

    private float per; // 주가수익비율

    private float eps; // 주당순이익

    private float pbr; // 주가순자산비율

}
