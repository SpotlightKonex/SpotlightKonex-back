package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.ResponseDTO;
import com.spotlightkonex.domain.dto.TalkResponseDTO;
import com.spotlightkonex.domain.entity.CompanyTalk;
import com.spotlightkonex.domain.entity.KonexStock;
import com.spotlightkonex.repository.CompanyTalkRepository;
import com.spotlightkonex.repository.KonexStockRepository;
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

    /**
     * 최신순 기업 댓글 조회
     * @param corpCode 기업 고유 코드
     * @return 해당 기업 댓글 리스트
     * */
    @Override
    public ResponseEntity<?> getCompanyTalkByCorpCode(String corpCode) {
        try {
            KonexStock konexStock = konexStockRepository.findByCorpCode(corpCode)
                    .orElseThrow(() -> new NullPointerException("잘못된 기업코드입니다."));

            List<CompanyTalk> companyTalkList = companyTalkRepository.findAllByKonexStockCorpCodeOrderByCreatedAtDesc(konexStock.getCorpCode())
                    .orElseThrow(() -> new NullPointerException("기업 채팅 내역이 없습니다."));

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
     * 이름 랜덤으로 생성하는 함수
     * */
    public String randomName(){
        String[] nameList = {"푹신한 토끼", "포근한 코알라", "작은 고슴도치", "건방진 다람쥐", "삐걱거리는 다람쥐", "퍼지 웜뱃", "꼭 껴안고 싶은 펭귄", "통통 튀는 캥거루", "스위트피 병아리", "낄낄거리는 팬더"};
        Random random = new Random();
        return nameList[random.nextInt(nameList.length)];
    }
}
