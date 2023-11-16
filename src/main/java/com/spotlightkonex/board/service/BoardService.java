package com.spotlightkonex.board.service;

import com.spotlightkonex.board.domain.dto.BoardRequestDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<?> createBoard(BoardRequestDto requestDto);
    ResponseEntity<?> searchBoards(String corpCode);
}
