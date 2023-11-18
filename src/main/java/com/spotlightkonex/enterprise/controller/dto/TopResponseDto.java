package com.spotlightkonex.enterprise.controller.dto;

public interface TopResponseDto {
    String getCorpCode(); //기업 고유번호
    String getCordName(); //기업 명
    String getLogo(); //로고
    Long getPrice(); //시세
    Long getCmpprevddPrc(); //전일대비지수
}
