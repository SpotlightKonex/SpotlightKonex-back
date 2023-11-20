package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.BoardPutRequestDto;
import com.spotlightkonex.domain.dto.BoardRequestDto;
import com.spotlightkonex.security.CompanyMemberDetails;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<?> createBoard(CompanyMemberDetails companyMemberDetails, BoardRequestDto requestDto);
    ResponseEntity<?> searchBoards(String corpCode);
    ResponseEntity<?> updateBoard(CompanyMemberDetails companyMemberDetails, BoardPutRequestDto requestDto);
    ResponseEntity<?> deleteBoard( CompanyMemberDetails companyMemberDetails, Long noticeSeq);
}
