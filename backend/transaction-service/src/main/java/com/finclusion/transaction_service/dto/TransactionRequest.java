package com.finclusion.transaction_service.dto;

import com.finclusion.transaction_service.entity.PaymentType;
import lombok.Data;

@Data
public class TransactionRequest {
    private String userId;
    private double amount;
    private PaymentType paymentType;
}
