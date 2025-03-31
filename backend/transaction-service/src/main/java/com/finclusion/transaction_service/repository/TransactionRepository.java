package com.finclusion.transaction_service.repository;

import com.finclusion.transaction_service.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {

    private final DynamoDbClient dynamoDbClient;

    public List<Transaction> getAllTransactions() {
        DynamoDbTable<Transaction> table = getTransactionTable();
        return table.scan(ScanEnhancedRequest.builder().build())
                .items()
                .stream()
                .collect(Collectors.toList());
    }

    // Get Table
    public DynamoDbTable<Transaction> getTransactionTable() {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        return enhancedClient.table("transaction-table", TableSchema.fromBean(Transaction.class));
    }

    // Save Transaction
    public void saveTransaction(Transaction transaction) {
        getTransactionTable().putItem(transaction);
    }

    // Get Transaction by ID
    public Transaction getTransactionById(String transactionId) {
        return getTransactionTable().getItem(r -> r.key(k -> k.partitionValue(transactionId)));
    }
}
