package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.BoardRequestDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<?> createBoard(BoardRequestDto requestDto);
    ResponseEntity<?> searchBoards(String corpCode);
    ResponseEntity<?> updateBoard(Long noticeSeq, BoardRequestDto requestDto);
    ResponseEntity<?> deleteBoard(Long noticeSeq);
}
