package com.example.spring_loanCalculator.transfer;


import com.example.spring_loanCalculator.model.PaymentFrequency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanCalculatorRequest {

    private double loanAmount;
    private double interestRate;
    private double numberOfPayments;
    private PaymentFrequency paymentFrequency;
}
