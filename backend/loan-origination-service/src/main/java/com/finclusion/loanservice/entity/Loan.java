package com.finclusion.loanservice.entity;

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
public class Loan {

    private String loanId;

    private String userId;

    private double amount;

    private String status; // PENDING, APPROVED, REJECTED

    private String appliedDate;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("loan_id")
    public String getLoanId() {
        return loanId;
    }

    @DynamoDbAttribute("user_id")
    public String getUserId() {
        return userId;
    }

    @DynamoDbAttribute("amount")
    public double getAmount() {
        return amount;
    }

    @DynamoDbAttribute("status")
    public String getStatus() {
        return status;
    }

    @DynamoDbAttribute("applied_date")
    public String getAppliedDate() {
        return appliedDate;
    }
}
