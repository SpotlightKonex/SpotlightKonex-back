package com.spotlightkonex.controller;

import com.spotlightkonex.service.BoardServiceImpl;
import com.spotlightkonex.service.CalculatorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorServiceImpl calculatorService;

    @GetMapping("/calculator")
    public Double getCalaulatorResult(@RequestParam Long income, Long investment){
        return calculatorService.getCalaulatorResult(income, investment);
    }
}
