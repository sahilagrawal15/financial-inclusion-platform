package com.finclusion.transaction_service.scheduler;

import com.finclusion.transaction_service.entity.Transaction;
import com.finclusion.transaction_service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduledPaymentProcessor {

    private final TransactionRepository transactionRepository;

    @Scheduled(cron = "0 0 2 * * *") // runs daily at 2 AM
    public void processScheduledPayments() {
        System.out.println("✅ [SCHEDULER STARTED] " + LocalDateTime.now());

        List<Transaction> pendingPayments = getTodayPendingPayments();
        if (pendingPayments.isEmpty()) {
            System.out.println("ℹ️ No scheduled payments to process today.");
            return;
        }

        for (Transaction txn : pendingPayments) {
            txn.setStatus("SUCCESS");
            transactionRepository.saveTransaction(txn);
            System.out.println("✅ Processed Transaction: " + txn.getTransactionId());
        }

        System.out.println("🎉 Processed " + pendingPayments.size() + " scheduled payments.");
    }

    public List<Transaction> getTodayPendingPayments() {
        List<Transaction> allTransactions = transactionRepository.getAllTransactions();
        String today = LocalDate.now().toString();

        return allTransactions.stream()
                .filter(txn -> "PENDING".equalsIgnoreCase(txn.getStatus()) &&
                        today.equals(txn.getPaymentDate()))
                .collect(Collectors.toList());
    }
}
