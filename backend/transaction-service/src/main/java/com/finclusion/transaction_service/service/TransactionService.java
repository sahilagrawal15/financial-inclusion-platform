package com.finclusion.transaction_service.service;

import com.finclusion.transaction_service.entity.PaymentType;
import com.finclusion.transaction_service.entity.Transaction;
import com.finclusion.transaction_service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public String makePayment(String userId, double amount, PaymentType paymentType) {
        String transactionId = UUID.randomUUID().toString();
        String status;
        LocalDate paymentDate;

        System.out.println("Payment type" + paymentType);

        if (paymentType == PaymentType.REALTIME) {
            status = "SUCCESS";
            paymentDate = LocalDate.now();
        } else {
            status = "PENDING";
            paymentDate = LocalDate.now().plusDays(2);
        }

        Transaction transaction = new Transaction(
                transactionId,
                userId,
                amount,
                paymentType,
                paymentDate.toString(),
                status
        );

        transactionRepository.saveTransaction(transaction);

        return transactionId;
    }

    public Transaction getTransactionStatus(String transactionId) {
        return transactionRepository.getTransactionById(transactionId);
    }
}
