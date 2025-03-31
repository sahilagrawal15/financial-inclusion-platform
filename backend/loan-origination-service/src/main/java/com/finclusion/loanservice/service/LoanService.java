package com.finclusion.loanservice.service;

import com.finclusion.loanservice.client.UserServiceClient;
import com.finclusion.loanservice.dto.LoanRequest;
import com.finclusion.loanservice.entity.Loan;
import com.finclusion.loanservice.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserServiceClient userServiceClient;

    public String applyForLoan(LoanRequest request, String token) {
        boolean isValid = userServiceClient.verifyUser(request.getUserId(), token);
        if (!isValid) {
            throw new RuntimeException("Unauthorized User");
        }

        String loanId = UUID.randomUUID().toString();

        Loan loan = new Loan(
                loanId,
                request.getUserId(),
                request.getAmount(),
                "PENDING",
                LocalDate.now().toString()
        );

        loanRepository.saveLoan(loan);

        return loanId;
    }

    public Loan getLoanById(String loanId) {
        return loanRepository.getLoanById(loanId);
    }
}
