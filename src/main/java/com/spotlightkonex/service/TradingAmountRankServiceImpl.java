package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.ResponseDTO;
import com.spotlightkonex.domain.dto.TradingAmountRankResponseDTO;
import com.spotlightkonex.domain.entity.KonexDetail;
import com.spotlightkonex.domain.entity.KonexStock;
import com.spotlightkonex.domain.entity.TradingAmountRank;
import com.spotlightkonex.repository.KonexDetailRepository;
import com.spotlightkonex.repository.KonexStockRepository;
import com.spotlightkonex.repository.TradingAmountRankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TradingAmountRankServiceImpl implements TradingAmountRankService {
    private final KonexStockRepository konexStockRepository;
    private final KonexDetailRepository konexDetailRepository;
    private final TradingAmountRankRepository tradingAmountRankRepository;

    /**
     * 거래대금 기준 순위변동 조회
     * @param corpCode 조회할 기업 고유코드
     * @return 기업 순위 리스트(1차 ~ 4차)
     * */
    @Override
    public ResponseEntity<?> getTradingAmountRankByCorpCode(String corpCode) {
        try {
            KonexStock konexStock = konexStockRepository.findByCorpCode(corpCode)
                    .orElseThrow(() -> new NullPointerException("해당하는 기업을 찾을 수 없습니다."));

            List<TradingAmountRank> tradingAmountRankList = tradingAmountRankRepository.findAllByKonexStockCorpCodeOrderByCreatedAtDesc(konexStock.getCorpCode())
                    .orElseThrow(() -> new NullPointerException("랭킹 기록이 없습니다."));

            List<TradingAmountRankResponseDTO> result = new ArrayList<>();
            int i = 0; //4번 체크하기 위한 변수
            for(TradingAmountRank rank : tradingAmountRankList){
                if(i==4) break;
                result.add(TradingAmountRankResponseDTO.builder()
                        .ranking(rank.getRanking())
                        .day(rank.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                        .build());
                i++;
            }
            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().message(e.getMessage()).build();
            return ResponseEntity
                    .internalServerError() //500
                    .body(responseDTO);
        }
    }

    /**
     * 장 마감 후, 거래대금 순 랭킹 등록(오후 3시 반)
     * */
    @Scheduled(cron = "0 30 15 ? * MON-FRI") //주중 15:30마다 실행
    public void setTradingAmountRank(){
        List<KonexDetail> konexDetailList = konexDetailRepository.findAllByCreatedAtOrderByTradingAmountDesc(LocalDate.now().toString())
                .orElseThrow(() -> new NullPointerException("거래대금 순 기업 정보 조회에 실패했습니다."));

        int i = 1; //1등부터
        for(KonexDetail detail: konexDetailList){
            TradingAmountRank tradingAmountRank = new TradingAmountRank(i, detail.getKonexStock()); //순위, 기업
            tradingAmountRankRepository.save(tradingAmountRank); //저장
            i++;
        }
    }
}