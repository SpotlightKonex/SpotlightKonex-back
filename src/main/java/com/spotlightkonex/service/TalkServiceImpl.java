package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.ResponseDTO;
import com.spotlightkonex.domain.dto.TalkRequestDTO;
import com.spotlightkonex.domain.dto.TalkResponseDTO;
import com.spotlightkonex.domain.entity.CompanyMember;
import com.spotlightkonex.domain.entity.CompanyTalk;
import com.spotlightkonex.domain.entity.KonexStock;
import com.spotlightkonex.repository.CompanyMemberRepository;
import com.spotlightkonex.repository.CompanyTalkRepository;
import com.spotlightkonex.repository.KonexStockRepository;
import com.spotlightkonex.security.CompanyMemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class TalkServiceImpl implements TalkService{
    private final KonexStockRepository konexStockRepository;
    private final CompanyTalkRepository companyTalkRepository;
    private final CompanyMemberRepository companyMemberRepository;

    /**
     * 최신순 기업 댓글 조회
     * @param corpCode 기업 고유 코드
     * @param status 기업 댓글 모아보기 여부 - ture: 모아보기, false: 섞어보기
     * @return 해당 기업 댓글 리스트
     * */
    @Override
    public ResponseEntity<?> getCompanyTalkByCorpCode(String corpCode, boolean status) {
        try {
            KonexStock konexStock = konexStockRepository.findByCorpCode(corpCode)
                    .orElseThrow(() -> new NullPointerException("잘못된 기업코드입니다."));

            List<CompanyTalk> companyTalkList;
            if(status){ //모아보기일 때
                companyTalkList = companyTalkRepository.findAllByKonexStockCorpCodeAndWriterTypeOrderByCreatedAtAsc(konexStock.getCorpCode(), 1)
                        .orElseThrow(() -> new NullPointerException("기업 채팅 내역이 없습니다."));
            } else{ //섞어보기
                companyTalkList = companyTalkRepository.findAllByKonexStockCorpCodeOrderByCreatedAtAsc(konexStock.getCorpCode())
                        .orElseThrow(() -> new NullPointerException("기업 채팅 내역이 없습니다."));
            }

            List<TalkResponseDTO> responseDTOList  = new ArrayList<>();
            for(CompanyTalk talk: companyTalkList){
                responseDTOList.add(TalkResponseDTO
                        .builder()
                        .context(talk.getContext())
                        .corpCode(talk.getKonexStock().getCorpCode())
                        .writerType(talk.getWriterType())
                        .nickname(talk.getNickname())
                        .build());
            }
            return ResponseEntity.ok().body(responseDTOList);
        } catch (Exception e){
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().message(e.getMessage()).build();
            return ResponseEntity
                    .internalServerError() //500
                    .body(responseDTO);
        }
    }

    /**
     * 기업 댓글 작성
     * @param companyMemberDetails 로그인 인증 정보
     * @param talkRequestDTO 작성한 댓글 정보 및 작성자
     * @return 완료 여부
     * */
    @Override
    public ResponseEntity<?> writeCompanyTalkByCorpCode(CompanyMemberDetails companyMemberDetails, TalkRequestDTO talkRequestDTO) {
        try{
            KonexStock konexStock = konexStockRepository.findByCorpCode(talkRequestDTO.getCorpCode())
                    .orElseThrow(() -> new NullPointerException("잘못된 기업코드입니다."));

            if(talkRequestDTO.getContext() == null) //작성된 내용이 없을 때
                return ResponseEntity.noContent().build();

            boolean isCompanyManger = false; //해당 기업 관리자 여부
            if(talkRequestDTO.getEmail() != null && companyMemberDetails != null){ //로그인된 사용자일 때
                if(!talkRequestDTO.getEmail().equals(companyMemberDetails.getEmail()))
                    return ResponseEntity.status(403).body("인증된 사용자가 아닙니다.");

                CompanyMember member = companyMemberRepository.findByEmail(companyMemberDetails.getEmail())
                        .orElseThrow(() -> new NullPointerException("관리자 이메일에 해당하는 가입 정보가 없습니다"));

                // 로그인된 기업 담당자의 기업코드와 댓글을 입력한 기업코드가 동일할 때 == 관리자일때
                if(member.getKonexStock().getCorpCode().equals(konexStock.getCorpCode()))
                    isCompanyManger = true;
            }

            CompanyTalk companyTalk = CompanyTalk.builder()
                    .context(talkRequestDTO.getContext())
                    .writerType(isCompanyManger ? 1 : 0) //0: 일반사용자, 1: 기업 담당자
                    .nickname(isCompanyManger ? "기업 담당자" : randomName())
                    .konexStock(konexStock)
                    .build();
            companyTalkRepository.save(companyTalk);

            return ResponseEntity.ok().body("댓글 등록에 성공했습니다.");
        } catch (Exception e){
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().message(e.getMessage()).build();
            return ResponseEntity
                    .internalServerError() //500
                    .body(responseDTO);
        }
    }

    /**
     * 이름 랜덤으로 생성하는 함수
     * */
    public String randomName(){
        String[] nameList = {"푹신한 토끼", "포근한 코알라", "작은 고슴도치", "건방진 다람쥐", "삐걱거리는 다람쥐", "퍼지 웜뱃", "꼭 껴안고 싶은 펭귄", "통통 튀는 캥거루", "스위트피 병아리", "낄낄거리는 팬더"};
        Random random = new Random();
        return nameList[random.nextInt(nameList.length)];
    }
}
