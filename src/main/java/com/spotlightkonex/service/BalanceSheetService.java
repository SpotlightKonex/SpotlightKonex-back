package com.spotlightkonex.service;

import com.spotlightkonex.repository.BalanceSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceSheetService {
    private final BalanceSheetRepository balanceSheetRepository;


}
