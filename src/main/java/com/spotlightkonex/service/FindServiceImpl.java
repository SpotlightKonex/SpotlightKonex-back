package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.KonexStockDTO;
import com.spotlightkonex.domain.dto.ResponseDTO;
import com.spotlightkonex.domain.entity.KonexStock;
import com.spotlightkonex.domain.cropName;
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
            List<KonexStockDTO> result = new ArrayList<>();

            // 키워드로 종목 검색
            List<KonexStock> konexStockList = konexStockRepository.findAllByStockCodeContaining(keyword);
            if(konexStockList != null){ //조회한 데이터가 있다면
                for(KonexStock k : konexStockList){
                    result.add(KonexStockDTO
                            .builder()
                            .corpCode(k.getCorpCode())
                            .corpName(k.getCorpName())
                            .logo(k.getLogo())
                            .build()
                    );
                }
            }

            // 키워드로 기업명 검색
            List<KonexStock> konexStockList2 = konexStockRepository.findAllByCorpNameContaining(keyword);
            if(konexStockList2 != null){ //조회한 데이터가 있다면
                for(KonexStock k : konexStockList2){
                    result.add(KonexStockDTO
                            .builder()
                            .corpCode(k.getCorpCode())
                            .corpName(k.getCorpName())
                            .logo(k.getLogo())
                            .build()
                    );
                }
            }

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
                enterprises = cropName.NUM1.getEnterprise();
            } else if (theme == 2) {
                enterprises = cropName.NUM2.getEnterprise();
            } else if (theme == 3) {
                enterprises = cropName.NUM3.getEnterprise();
            } else if (theme == 4) {
                enterprises = cropName.NUM4.getEnterprise();
            } else if (theme == 5) {
                enterprises = cropName.NUM5.getEnterprise();
            } else if (theme == 6) {
                enterprises = cropName.NUM6.getEnterprise();
            } else if (theme == 7) {
                enterprises = cropName.NUM7.getEnterprise();
            } else if (theme == 8) {
                enterprises = cropName.NUM8.getEnterprise();
            }

            if(enterprises == null)
                throw new RuntimeException("검색할 테마가 없습니다.");

            List<KonexStockDTO> result = new ArrayList<>(); //결과로 전달할 리스트
            for(String indutyName : enterprises){
                List<KonexStock> konexStockList = konexStockRepository.findAllByIndutyNameContaining(indutyName);
                if(konexStockList != null){ //조회한 데이터가 있다면
                    for(KonexStock k : konexStockList){
                        result.add(KonexStockDTO
                                .builder()
                                .corpCode(k.getCorpCode())
                                .corpName(k.getCorpName())
                                .logo(k.getLogo())
                                .build()
                        );
                    }
                }
            }
            if(result.isEmpty())
                throw new NullPointerException("테마별 기업 조회 결과가 없습니다.");

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
    public ResponseEntity<?> getEnterpriseByNominateAdviser(String advisor) throws RuntimeException {
        try {
            if(advisor == null){
                throw new RuntimeException("요청한 지정자문인이 없습니다.");
            }

            List<KonexStockDTO> result = new ArrayList<>(); //결과로 전달할 리스트
            List<KonexStock> konexList = new ArrayList<>();

            if(advisor.equals("기타")){
                List<KonexStock> konexStockList1 = konexStockRepository.findByNominateAdviserContaining("한양증권(주)");
                List<KonexStock> konexStockList2 = konexStockRepository.findByNominateAdviserContaining("유안타증권(주)");

                //조회한 두 리스트 합치기
                if(konexStockList1 != null) konexList.addAll(konexStockList1);
                if(konexStockList2 != null) konexList.addAll(konexStockList2);
            } else{
                konexList = konexStockRepository.findByNominateAdviserContaining(advisor);
            }

            if(konexList.isEmpty())
                throw new NullPointerException("지정자문인별 조회 결과가 없습니다.");

            for(KonexStock k : konexList){
                result.add(KonexStockDTO
                        .builder()
                        .corpCode(k.getCorpCode())
                        .corpName(k.getCorpName())
                        .logo(k.getLogo())
                        .build()
                );
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