package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.EnterpriseDetailDTO;
import com.spotlightkonex.domain.dto.EnterpriseDetailRequestDto;
import com.spotlightkonex.domain.dto.ResponseDTO;
import com.spotlightkonex.domain.entity.KonexStock;
import com.spotlightkonex.repository.KonexStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EnterpriseDetailServiceImpl implements EnterpriseDetailService{
    private final KonexStockRepository konexStockRepository;
    /**
     * 기업 상세 조회
     * @param corpCode 조회할 기업 고유코드
     * @return 기업 상세 정보
     * */
    @Override
    public ResponseEntity<?> getCompanyDetailByCorpCode(String corpCode) {
        try {
            KonexStock konexStock = konexStockRepository.findByCorpCode(corpCode)
                    .orElseThrow(() -> new NullPointerException("해당하는 기업을 찾을 수 없습니다."));

            EnterpriseDetailDTO detailDTO = EnterpriseDetailDTO.builder()
                    .corpName(konexStock.getCorpName())
                    .indutyName(konexStock.getIndutyName())
                    .establish_date(konexStock.getEstablishDate())
                    .public_date(konexStock.getPublicDate())
                    .capital(konexStock.getCapital())
                    .address(konexStock.getAddress())
                    .url(konexStock.getUrl())
                    .description(konexStock.getDescription())
                    .build();

            return ResponseEntity.ok().body(detailDTO);
        }catch (Exception e){
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().message(e.getMessage()).build();
            return ResponseEntity
                    .internalServerError() //500
                    .body(responseDTO);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> modifyCompanyDescription(EnterpriseDetailRequestDto enterpriseDetailRequestDto) {
        try {
            KonexStock konexStock = konexStockRepository.findByCorpCode(enterpriseDetailRequestDto.getCorpCode())
                    .orElseThrow(() -> new NullPointerException("해당하는 기업을 찾을 수 없습니다."));

            konexStock.setDescription(enterpriseDetailRequestDto.getDescription());

            EnterpriseDetailDTO detailDTO = EnterpriseDetailDTO.builder()
                    .corpName(konexStock.getCorpName())
                    .indutyName(konexStock.getIndutyName())
                    .establish_date(konexStock.getEstablishDate())
                    .public_date(konexStock.getPublicDate())
                    .capital(konexStock.getCapital())
                    .address(konexStock.getAddress())
                    .url(konexStock.getUrl())
                    .description(konexStock.getDescription())
                    .build();

            return ResponseEntity.ok().body(detailDTO);

        } catch (Exception e) {
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().message(e.getMessage()).build();
            return ResponseEntity
                    .internalServerError() //500
                    .body(responseDTO);
        }

    }
}
