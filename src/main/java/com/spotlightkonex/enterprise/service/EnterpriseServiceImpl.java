package com.spotlightkonex.enterprise.service;

import com.spotlightkonex.enterprise.controller.dto.EnterpriseDto;
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
}
