package com.spotlightkonex.board.service;

import com.spotlightkonex.board.domain.dto.BoardRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface BoardService {
    ResponseEntity<?> createBoard(BoardRequestDto requestDto);
    ResponseEntity<?> searchBoards(String corpCode);
    ResponseEntity<?> updateBoard(Long noticeSeq, BoardRequestDto requestDto);
    ResponseEntity<?> deleteBoard(Long noticeSeq);
}
