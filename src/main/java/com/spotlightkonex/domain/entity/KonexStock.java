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
@Table(name ="konex_stock")
public class KonexStock {
    @Id
    @Column(name="corp_code")
    private String corpCode; //기업 고유번호

    @Column(name="corp_name", unique = true)
    private String corpName; //종목명(법인명)

    @Column(name="stock_code", unique = true)
    private String stockCode; //종목 코드

    @Column(name="ceo_name")
    private String ceoName; //대표자명

    private String address; //주소

    @Column(name="home_url")
    private String homeUrl; //홈페이지

    @Column(name="induty_code", nullable = false)
    private String indutyCode; //업종코드

    @Column(name="induty_name", nullable = false)
    private String indutyName; //업종명

    @Column(name="establish_date")
    private LocalDateTime establishDate; //설립일

    @Column(name="public_date")
    private LocalDateTime publicDate; //상장일

    private Long capital; //자본금

    private String logo; //로고

    @Column(name="nominate_adviser", nullable = false)
    private String nominateAdviser; //지정자문인

    @CreatedDate
    @Column(name="created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt; //생성일
}
