package com.finclusion.loanservice.dto;

import lombok.Data;

@Data
public class LoanRequest {
    private String userId;
    private double amount;
}
