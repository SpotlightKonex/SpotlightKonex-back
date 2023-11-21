package com.spotlightkonex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalaulatorService {

    @Override
    public Double getTax(Long money) {
        double taxRate;
        double tax;

        if (money <= 14000000) {
            taxRate = 0.06;
        } else if (money <= 50000000) {
            taxRate = 0.15;
        } else if (money <= 88000000) {
            taxRate = 0.24;
        } else if (money <= 150000000) {
            taxRate = 0.35;
        } else if (money <= 300000000) {
            taxRate = 0.38;
        } else if (money <= 500000000) {
            taxRate = 0.42;
        } else if (money <= 1000000000) {
            taxRate = 0.42;
        } else {
            taxRate = 0.45;
        }

        if (money <= 14000000) {
            tax = money * taxRate;
        } else if (money <= 50000000) {
            tax = 14000000 * 0.06 + (money - 14000000) * taxRate;
        } else if (money <= 88000000) {
            tax = 14000000 * 0.06 + (50000000 - 14000000) * 0.15 + (money - 50000000) * taxRate;
        } else if (money <= 150000000) {
            tax = 14000000 * 0.06 + (50000000 - 14000000) * 0.15 + (88000000 - 50000000) * 0.24 + (money - 88000000) * taxRate;
        } else if (money <= 300000000) {
            tax = 14000000 * 0.06 + (50000000 - 14000000) * 0.15 + (88000000 - 50000000) * 0.24 + (150000000 - 88000000) * 0.35 + (money - 150000000) * taxRate;
        } else if (money <= 500000000) {
            tax = 14000000 * 0.06 + (50000000 - 14000000) * 0.15 + (88000000 - 50000000) * 0.24 + (150000000 - 88000000) * 0.35 + (300000000 - 150000000) * 0.4 + (money - 300000000) * taxRate;
        } else if (money <= 1000000000) {
            tax = 14000000 * 0.06 + (50000000 - 14000000) * 0.15 + (88000000 - 50000000) * 0.24 + (150000000 - 88000000) * 0.35 + (300000000 - 150000000) * 0.4 + (500000000 - 300000000) * 0.42 + (money - 500000000);
        } else {
            tax = 14000000 * 0.06 + (50000000 - 14000000) * 0.15 + (88000000 - 50000000) * 0.24 + (150000000 - 88000000) * 0.35 + (300000000 - 150000000) * 0.4 + (500000000 - 300000000) * 0.42 + (1000000000 - 500000000) + (money - 1000000000) * taxRate;
        }
        return tax;
    }

    @Override
    public Double getCalaulatorResult(Long income, Long investment) {
        Double incomeTax = getTax(income);
        Double angelTax = getTax(income - investment);

        return incomeTax - angelTax;
    }
}

