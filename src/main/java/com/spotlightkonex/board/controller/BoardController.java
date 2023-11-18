package com.spotlightkonex.board.controller;

import com.spotlightkonex.board.domain.dto.BoardRequestDto;
import com.spotlightkonex.board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // JSON으로 데이터 주고 받음
@RequiredArgsConstructor
public class BoardController {
    private final BoardServiceImpl boardService;

//    public BoardController(BoardService boardService) {
//        this.boardService = boardService;
//    }

    /**
     * 게시물 등록
     * @return ResponseEntity
     */
    @PostMapping("/boards")
    public ResponseEntity<?> createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    /**
     * 기업 게시물 조회
     * @param corp_code
     * @return ResponseEntity
     */
    @GetMapping("/boards/{corp_code}")
    public ResponseEntity<?> searchBoards(@PathVariable String corp_code) {
        return boardService.searchBoards(corp_code);
    }

    /**
     * 기업 게시물 수정
     * @param notice_seq
     * @param requestDto
     * @return ResponseEntity
     */
    @PutMapping("/boards/{notice_seq}")
    public ResponseEntity<?> updateBoard(@PathVariable Long notice_seq, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(notice_seq, requestDto);
    }

    /**
     * 기업 게시물 삭제
     * @param notice_seq
     * @return ResponseEntity
     */
    @DeleteMapping("/boards/{notice_seq}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long notice_seq) {
        return boardService.deleteBoard(notice_seq);
    }

}
