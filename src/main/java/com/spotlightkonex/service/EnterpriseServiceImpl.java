package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.ResponseDTO;
import com.spotlightkonex.domain.dto.EnterpriseResponseDTO;
import com.spotlightkonex.repository.KonexDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EnterpriseServiceImpl implements EnterpriseService {
    private final KonexDetailRepository konexDetailRepository;

    /**
     * 모든 코넥스 기업 조회
     * @return 모든 코넥스 기업 리스트
     * */
    @Override
    public ResponseEntity<?> getEnterprise() {
        try {
            List<EnterpriseResponseDTO> enterpriseDtoList = konexDetailRepository.getAllEnterprise()
                    .orElseThrow(() -> new NullPointerException("기업 정보를 불러오는 것을 실패했습니다."));
            return ResponseEntity.ok().body(enterpriseDtoList);
        } catch (Exception e){
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().message(e.getMessage()).build();
            return ResponseEntity
                    .internalServerError()
                    .body(responseDTO);
        }
    }

    /**
     * 거래대금, 좋아요, 조회수 top 11 조회
     * @param rankType 랭크 타입
     * @return 랭크 타입에 해당하는 top 11 기업 리스트
     * */
    @Override
    public ResponseEntity<?> getEnterpriseByRankTpye(String rankType){
        try {
            if(rankType == null){ //rankType이 비어있다면
                return ResponseEntity.noContent().build(); //204 리턴
            }

            System.out.println("rankType : " + rankType);

            List<EnterpriseResponseDTO> enterpriseTopDtoList; //받아온 결과를 담을 리스트
            switch (rankType) {
                case "amount" -> enterpriseTopDtoList = konexDetailRepository.getTop11ByTransactionAmount()
                        .orElseThrow(() -> new NullPointerException("거래대금 top 11 조회에 실패했습니다."));
                case "like" -> enterpriseTopDtoList = konexDetailRepository.getTop11ByLike()
                        .orElseThrow(() -> new NullPointerException("좋아요수 top 11 조회에 실패했습니다."));
                case "views" -> enterpriseTopDtoList = konexDetailRepository.getTop11ByViews()
                        .orElseThrow(() -> new NullPointerException("조회수 top 11 조회에 실패했습니다."));
                default -> { //일치하지 않을 때
                    return ResponseEntity.notFound().build(); //404 리턴
                }
            }
            return ResponseEntity.ok().body(enterpriseTopDtoList); //200
        } catch (Exception e){
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().message(e.getMessage()).build();
            return ResponseEntity
                    .internalServerError() //500
                    .body(responseDTO);
        }
    }
}
