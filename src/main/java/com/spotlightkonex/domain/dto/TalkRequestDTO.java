package com.spotlightkonex.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class TalkRequestDTO {
    public final String context; //채팅 내역
    public final String corpCode; //기업 고유번호
    public final String email; //기업 이메일
}
