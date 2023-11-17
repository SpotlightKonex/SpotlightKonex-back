package com.spotlightkonex.board.service;

import com.spotlightkonex.board.domain.dto.BoardListResponseDto;
import com.spotlightkonex.board.domain.entity.KonexStock;
import com.spotlightkonex.board.respository.KonexStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.spotlightkonex.board.domain.dto.BoardRequestDto;
import com.spotlightkonex.board.domain.entity.Board;
import com.spotlightkonex.board.respository.BoardRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final KonexStockRepository konexStockRepository;

    @Override
    public ResponseEntity<?> createBoard(BoardRequestDto requestDto) {
        try {
            String corpCode = requestDto.getCorp_code(); //requestDto에 있는 corp_code의 값을 cropCode에 담기
            KonexStock konexStock = konexStockRepository.findByCorpCode(corpCode); //KonexStock 표 전체 내용(해당기업)
            if (konexStock == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("잘못된 기업 코드 입니다" + corpCode);// 404(Not Found)
            }
            Board board = Board.builder()
                    .title(requestDto.getTitle())
                    .context(requestDto.getContext())
                    .konexStock(konexStock)
                    .build();
            boardRepository.save(board);
            return new ResponseEntity<>("게시물 등록 성공", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("게시물 등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 해당 기업 게시물 가져오기
    @Override
    public ResponseEntity<?> searchBoards(String corpCode) {
        try {
            List<Board> boardList = boardRepository.findByKonexStockCorpCodeOrderByModifiedAtDesc(corpCode);

            if (boardList == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("해당 기업의 게시물이 존재하지 않습니다." + corpCode);// 404(Not Found)
            }
            List<BoardListResponseDto> responseDtoList = new ArrayList<>();

            for (Board board : boardList) {
//                BoardListResponseDto responseDto = new BoardListResponseDto(board);
//                responseDtoList.add(responseDto);
                BoardListResponseDto dto = BoardListResponseDto.builder()
                        .corpCode(board.getKonexStock().getCorpCode())
                        .title(board.getTitle())
                        .context(board.getContext())
                        .build();
                responseDtoList.add(dto);
            }
            return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("게시물 조회 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 게시물 수정
    @Override
    @Transactional
    public ResponseEntity<?> updateBoard(Long noticeSeq, BoardRequestDto requestDto) {
        try {
            Board board = boardRepository.findByNoticeSeq(noticeSeq);

            if (board == null) {
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("해당 번호의 게시글이 존재하지 않습니다.");// 404(Not Found)
            }
            String corpCode = requestDto.getCorp_code(); //requestDto에 있는 corp_code의 값을 cropCode에 담기
            KonexStock konexStock = konexStockRepository.findByCorpCode(corpCode); //KonexStock 표 전체 내용(해당기업)
            board = Board.builder()
                    .title(requestDto.getTitle())
                    .context(requestDto.getContext())
                    .konexStock(konexStock)
                    .build();
            boardRepository.save(board);

            return new ResponseEntity<>("게시물 수정 성공", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("게시물 수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 게시물 삭제
    @Override
    @Transactional
    public ResponseEntity<?> deleteBoard(Long noticeSeq) {
        try {
            Board board = boardRepository.findByNoticeSeq(noticeSeq);

            if (board == null) {
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("해당 번호의 게시물이 존재하지 않습니다.");// 404(Not Found)
            }
            boardRepository.deleteById(noticeSeq);
            return new ResponseEntity<>("게시물 삭제 성공", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("게시물 삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


