package com.finclusion.loanservice.controller;

import com.finclusion.loanservice.dto.LoanRequest;
import com.finclusion.loanservice.entity.Loan;
import com.finclusion.loanservice.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<String> applyForLoan(
            @RequestHeader("Authorization") String token,
            @RequestBody LoanRequest request) {
        String loanId = loanService.applyForLoan(request, token);
        return ResponseEntity.ok("Loan Applied. Loan ID: " + loanId);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoan(@PathVariable String loanId) {
        Loan loan = loanService.getLoanById(loanId);
        return ResponseEntity.ok(loan);
    }
}
