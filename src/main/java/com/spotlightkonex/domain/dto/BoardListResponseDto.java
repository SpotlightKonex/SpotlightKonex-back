package com.spotlightkonex.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BoardListResponseDto {
    // 기업 코드
    private String corpCode;

    // 제목
    private String title;

    // 내용
    private String context;

    // 게시판 수
    private Long noticeSeq;

    // 생성일
//    private LocalDateTime createdAt;

    // 수정일
//    private LocalDateTime modifiedAt;


    // Entity -> dto
//    public BoardListResponseDto(Board board) {
//        this.title = board.getTitle();
//        this.corpCode = board.getKonex().getCorpCode();
//        this.context = board.getContext();
//        this.createdAt = board.getCreatedAt();
//        this.modifiedAt = board.getModifiedAt();
//    }

}
