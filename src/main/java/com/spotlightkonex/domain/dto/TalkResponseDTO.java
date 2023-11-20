package com.spotlightkonex.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class TalkResponseDTO {
    public final String context; //채팅 내역
    public final int writerType; //작성자 구분
    public final String corpCode; //기업 고유번호
    public final String nickname; //닉네임
}
