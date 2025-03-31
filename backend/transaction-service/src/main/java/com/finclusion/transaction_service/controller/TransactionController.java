package com.finclusion.transaction_service.controller;

import com.finclusion.transaction_service.dto.TransactionRequest;
import com.finclusion.transaction_service.entity.PaymentType;
import com.finclusion.transaction_service.entity.Transaction;
import com.finclusion.transaction_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/payment")
    public ResponseEntity<String> makePayment(@RequestBody TransactionRequest request) {
        try {
            String transactionId = transactionService.makePayment(
                    request.getUserId(), request.getAmount(), request.getPaymentType());
            return ResponseEntity.ok("Payment Created. Transaction ID: " + transactionId);
        } catch (Exception e) {
            e.printStackTrace(); // Log actual error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred: " + e.getMessage());
        }
    }


    @GetMapping("/payment/{transactionId}")
    public ResponseEntity<Transaction> getPaymentStatus(@PathVariable String transactionId) {
        Transaction transaction = transactionService.getTransactionStatus(transactionId);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }
}
