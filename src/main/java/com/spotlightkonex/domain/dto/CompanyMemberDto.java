package com.spotlightkonex.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CompanyMemberDto {
    private Long memberSeq;
    private String email; // 관리자 이메일
    private String password; // 패스워드
    private String corpName; // 기업 명
    private String phone; // 관리자 연락처
    private String corpCode; // 기업 고유번호
}
