package com.spotlightkonex.board.respository;

import com.spotlightkonex.board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
//    List<Board> findAllByKonexCorpCodeOrderByModifiedAtDesc(String corpCode);
    List<Board> findByKonexStockCorpCodeOrderByModifiedAtDesc(String corpCode);

    Board findByNoticeSeq(Long noticeSeq);

}
