package com.spotlightkonex.domain.dto;

import com.spotlightkonex.domain.entity.CompanyMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CompanyMemberResponseDto {
    private String message;
    private Long memberSeq;

    public CompanyMemberResponseDto(CompanyMember companyMember) {
        this.memberSeq = companyMember.getMemberSeq();
    }
}
