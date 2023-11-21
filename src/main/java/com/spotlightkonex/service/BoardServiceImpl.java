package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.BoardListResponseDto;
import com.spotlightkonex.domain.dto.BoardPutRequestDto;
import com.spotlightkonex.domain.entity.CompanyMember;
import com.spotlightkonex.domain.entity.KonexStock;
import com.spotlightkonex.repository.CompanyMemberRepository;
import com.spotlightkonex.repository.KonexStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.spotlightkonex.domain.dto.BoardRequestDto;
import com.spotlightkonex.domain.entity.Board;
import com.spotlightkonex.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final KonexStockRepository konexStockRepository;
    private final CompanyMemberRepository companyMemberRepository;

    @Override
    public ResponseEntity<?> createBoard(BoardRequestDto requestDto) {
        try {
            String requestEmail = requestDto.getEmail();
            String corpCode = requestDto.getCorpCode(); //requestDto에 있는 corp_code의 값을 cropCode에 담기
            KonexStock konexStock = konexStockRepository.findByCorpCode(corpCode)
                    .orElseThrow(() -> new NullPointerException("잘못된 기업 코드입니다.")); //KonexStock 표 전체 내용(해당기업)

            CompanyMember companyMember = companyMemberRepository.findByEmail(requestEmail)
                    .orElseThrow(() -> new NullPointerException("해당 이메일을 가진 멤버가 존재하지 않습니다."));

            if (!companyMember.getKonexStock().equals(konexStock)) {
                throw new NullPointerException("허용되지 않는 사용자 입니다");
            }
            Board board = Board.builder()
                    .title(requestDto.getTitle())
                    .context(requestDto.getContext())
                    .konexStock(companyMember.getKonexStock())
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
                        .noticeSeq(board.getNoticeSeq())
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
    public ResponseEntity<?> updateBoard(BoardPutRequestDto requestDto) {
        try {
            Board board = boardRepository.findByNoticeSeq(requestDto.getNoticeSeq());

            if (board == null) {
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("해당 번호의 게시글이 존재하지 않습니다.");// 404(Not Found)
            }
            String corpCode = requestDto.getCorpCode(); //requestDto에 있는 corp_code의 값을 cropCode에 담기
            KonexStock konexStock = konexStockRepository.findByCorpCode(corpCode)
                    .orElseThrow(() -> new NullPointerException("해당하는 기업이 없습니다.")); //KonexStock 표 전체 내용(해당기업)

            board.setTitle(requestDto.getTitle());
            board.setContext(requestDto.getContext());
            board.setKonexStock(konexStock);
            boardRepository.save(board);
//            Board newBoard = Board.builder()
//                    .title(requestDto.getTitle())
//                    .context(requestDto.getContext())
//                    .konexStock(konexStock)
//                    .build();
//            boardRepository.save(newBoard);

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