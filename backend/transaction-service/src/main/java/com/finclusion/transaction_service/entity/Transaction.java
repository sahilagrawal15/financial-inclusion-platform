package com.finclusion.transaction_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class Transaction {

    private String transactionId;
    private String userId;
    private double amount;
    private PaymentType paymentType;
    private String paymentDate;
    private String status;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    @DynamoDbAttribute("user_id")
    public String getUserId() {
        return userId;
    }
}
