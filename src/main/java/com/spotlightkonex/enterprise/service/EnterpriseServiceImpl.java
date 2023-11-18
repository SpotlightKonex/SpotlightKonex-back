package com.spotlightkonex.enterprise.service;

import com.spotlightkonex.enterprise.controller.dto.EnterpriseDto;
import com.spotlightkonex.enterprise.controller.dto.TopResponseDto;
import com.spotlightkonex.enterprise.repository.EnterpriseRepository;
import com.spotlightkonex.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EnterpriseServiceImpl implements EnterpriseService {
    private final EnterpriseRepository enterpriseRepository;

    /**
     * 모든 코넥스 기업 조회
     * @return 모든 코넥스 기업 리스트
     * */
    @Override
    public ResponseEntity<?> getEnterprise() {
        try {
            List<EnterpriseDto> enterpriseDtoList = enterpriseRepository.getAllEnterprise()
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

            List<TopResponseDto> enterpriseTopDtoList; //받아온 결과를 담을 리스트
            switch (rankType){
                case "amount":
                    enterpriseTopDtoList = enterpriseRepository.getTop11ByTransactionAmount()
                            .orElseThrow(() -> new NullPointerException("거래대금 top 11 조회에 실패했습니다."));
                    break;
                case "like":
                    enterpriseTopDtoList = enterpriseRepository.getTop11ByLike()
                            .orElseThrow(() -> new NullPointerException("좋아요수 top 11 조회에 실패했습니다."));
                    break;
                case "views":
                    enterpriseTopDtoList = enterpriseRepository.getTop11ByViews()
                            .orElseThrow(() -> new NullPointerException("조회수 top 11 조회에 실패했습니다."));
                    break;
                default: //일치하지 않을 때
                    return ResponseEntity.notFound().build(); //404 리턴
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
