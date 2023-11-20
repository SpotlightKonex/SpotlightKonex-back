package com.spotlightkonex.domain.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TalkRequestDTO {
    public String context; //채팅 내역
    public String corpCode; //기업 고유번호
    public String email; //기업 이메일
}
