package com.finclusion.loanservice.repository;

import com.finclusion.loanservice.entity.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
@RequiredArgsConstructor
public class LoanRepository {

    private final DynamoDbClient dynamoDbClient;
    private final DynamoDbEnhancedClient enhancedClient;

    private DynamoDbTable<Loan> loanTable() {
        return enhancedClient.table("LoanTable", TableSchema.fromBean(Loan.class));
    }

    public void saveLoan(Loan loan) {
        loanTable().putItem(loan);
    }

    public Loan getLoanById(String loanId) {
        return loanTable().getItem(r -> r.key(k -> k.partitionValue(loanId)));
    }
}
