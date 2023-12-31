package com.spotlightkonex.service;

import com.spotlightkonex.domain.CorpNameEnum;
import com.spotlightkonex.domain.dto.EnterpriseResponseDTO;
import com.spotlightkonex.domain.dto.ResponseDTO;
import com.spotlightkonex.domain.entity.KonexStock;
import com.spotlightkonex.repository.KonexStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FindServiceImpl implements FindService{
    private final KonexStockRepository konexStockRepository;

    /**
     * 검색창 기업 검색
     * @author 이지은
     * @param keyword 검색 키워드
     * */
    @Override
    public ResponseEntity<?> getEnterpriseByKeyword(String keyword) {
        try {
            List<EnterpriseResponseDTO> result = new ArrayList<>();

            // 키워드로 종목 검색
            List<KonexStock> konexStockList = konexStockRepository.findAllByStockCodeContaining(keyword);
            if(konexStockList != null){ //조회한 데이터가 있다면
                for(KonexStock k : konexStockList){
                    //해당 기업의 시세 및 등락률 가져오기
                    EnterpriseResponseDTO responseDTO = konexStockRepository.findDetailByCorpCode(k.getCorpCode());
                    result.add(responseDTO);
                }
            }

            // 키워드로 기업명 검색
            List<KonexStock> konexStockList2 = konexStockRepository.findAllByCorpNameContaining(keyword);
            if(konexStockList2 != null){ //조회한 데이터가 있다면
                for(KonexStock k : konexStockList2){
                    //해당 기업의 시세 및 등락률 가져오기
                    EnterpriseResponseDTO responseDTO = konexStockRepository.findDetailByCorpCode(k.getCorpCode());
                    result.add(responseDTO);
                }
            }
            if(result.isEmpty())
                return ResponseEntity.noContent().build();

            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().message(e.getMessage()).build();
            return ResponseEntity
                    .internalServerError()
                    .body(responseDTO);
        }
    }

    /**
     * 테마별 기업 검색
     * @author 이지은
     * @param theme 테마 번호
     * */
    @Override
    public ResponseEntity<?> getEnterpriseByIndutyName(Long theme) throws RuntimeException {
        try{
            if(theme == null){
                throw new RuntimeException("요청한 테마가 없습니다.");
            }

            String [] enterprises = null;
            if (theme == 1) {
                enterprises = CorpNameEnum.NUM1.getEnterprise();
            } else if (theme == 2) {
                enterprises = CorpNameEnum.NUM2.getEnterprise();
            } else if (theme == 3) {
                enterprises = CorpNameEnum.NUM3.getEnterprise();
            } else if (theme == 4) {
                enterprises = CorpNameEnum.NUM4.getEnterprise();
            } else if (theme == 5) {
                enterprises = CorpNameEnum.NUM5.getEnterprise();
            } else if (theme == 6) {
                enterprises = CorpNameEnum.NUM6.getEnterprise();
            } else if (theme == 7) {
                enterprises = CorpNameEnum.NUM7.getEnterprise();
            } else if (theme == 8) {
                enterprises = CorpNameEnum.NUM8.getEnterprise();
            }

            if(enterprises == null)
                return ResponseEntity.notFound().build();

            List<EnterpriseResponseDTO> result = new ArrayList<>(); //결과로 전달할 리스트
            for(String indutyName : enterprises){
                List<KonexStock> konexStockList = konexStockRepository.findAllByIndutyNameContaining(indutyName);
                if(konexStockList != null){ //조회한 데이터가 있다면
                    for(KonexStock k : konexStockList){
                        //해당 기업의 시세 및 등락률 가져오기
                        EnterpriseResponseDTO responseDTO = konexStockRepository.findDetailByCorpCode(k.getCorpCode());
                        result.add(responseDTO);
                    }
                }
            }
            if(result.isEmpty())
                return ResponseEntity.noContent().build();

            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().message(e.getMessage()).build();
            return ResponseEntity
                    .internalServerError()
                    .body(responseDTO);
        }
    }

    /**
     * 지문자문인별 기업 검색
     * @author 이지은
     * @param advisor 지정자문인 명
     * */
    @Override
    public ResponseEntity<?> getEnterpriseByNominateAdviser(Long advisor) throws RuntimeException {
        try {
            //지정자문인 매칭
            String advisorName;
            if (advisor == 1) advisorName = "유진투자증권";
            else if (advisor == 2) advisorName = "신한투자증권 주식회사";
            else if (advisor == 3) advisorName = "하이투자증권";
            else if (advisor == 4) advisorName = "IBK투자증권";
            else if (advisor == 5) advisorName = "미래에셋증권 주식회사";
            else if (advisor == 6) advisorName = "SK증권";
            else if (advisor == 7) advisorName = "상상인증권";
            else if (advisor == 8) advisorName = "한화투자증권";
            else if (advisor == 9) advisorName = "대신증권";
            else if (advisor == 10) advisorName = "키움증권";
            else if (advisor == 11) advisorName = "하나증권주식회사";
            else if (advisor == 12) advisorName = "엔에이치투자증권주식회사";
            else if (advisor == 13) advisorName = "현대자동차증권주식회사";
            else if (advisor == 14) advisorName = "교보증권";
            else if (advisor == 15) advisorName = "비엔케이투자증권";
            else if (advisor == 16) advisorName = "신영증권";
            else if (advisor == 17) advisorName = "DB금융투자주식회사";
            else if (advisor == 18) advisorName = "한국투자증권";
            else if (advisor == 19) advisorName = "KB증권";
            else if (advisor == 20) advisorName = "기타";
            else throw new RuntimeException("요청한 지정자문인이 없습니다.");

            List<EnterpriseResponseDTO> result = new ArrayList<>(); //결과로 전달할 리스트
            List<KonexStock> konexList = new ArrayList<>();

            if(advisorName.equals("기타")){
                List<KonexStock> konexStockList1 = konexStockRepository.findByNominateAdviserContaining("한양증권");
                List<KonexStock> konexStockList2 = konexStockRepository.findByNominateAdviserContaining("유안타증권");

                //조회한 두 리스트 합치기
                if(konexStockList1 != null) konexList.addAll(konexStockList1);
                if(konexStockList2 != null) konexList.addAll(konexStockList2);
            } else{
                konexList = konexStockRepository.findByNominateAdviserContaining(advisorName);
            }

            if(konexList.isEmpty())
                throw new NullPointerException("지정자문인별 조회 결과가 없습니다.");

            for(KonexStock k : konexList){
                //해당 기업의 시세 및 등락률 가져오기
                EnterpriseResponseDTO responseDTO = konexStockRepository.findDetailByCorpCode(k.getCorpCode());
                result.add(responseDTO);
            }

            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().message(e.getMessage()).build();
            return ResponseEntity
                    .internalServerError()
                    .body(responseDTO);
        }
    }
}