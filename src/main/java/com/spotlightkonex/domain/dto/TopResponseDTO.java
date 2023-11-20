package com.spotlightkonex.domain.dto;

public interface TopResponseDTO {
    String getCorpCode(); //기업 고유번호
    String getCorpName(); //기업 명
    String getLogo(); //로고
    Long getPrice(); //시세
    String getCmpprevddPrc(); //전일대비지수
}
