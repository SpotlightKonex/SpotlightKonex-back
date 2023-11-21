package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.BoardRequestDto;
import org.springframework.http.ResponseEntity;

public interface CalaulatorService {

    Double getTax(Long money);
    Double getCalaulatorResult(Long income, Long investment);
}
