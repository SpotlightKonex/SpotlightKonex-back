package com.spotlightkonex.controller;

import com.spotlightkonex.domain.dto.BoardPutRequestDto;
import com.spotlightkonex.domain.dto.BoardRequestDto;
import com.spotlightkonex.service.BoardServiceImpl;
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
     * @param
     * @param requestDto
     * @return ResponseEntity
     */
    @PutMapping("/boards")
    public ResponseEntity<?> updateBoard(@RequestBody BoardPutRequestDto requestDto) {
        return boardService.updateBoard(requestDto);
    }

    /**
     * 기업 게시물 삭제
     * @param
     * @return ResponseEntity
     */
    @DeleteMapping("/boards")
    public ResponseEntity<?> deleteBoard(@RequestBody Long noticeSeq) {
        return boardService.deleteBoard(noticeSeq);
    }

}