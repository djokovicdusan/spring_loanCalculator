package com.example.spring_loanCalculator.transfer;


import com.example.spring_loanCalculator.model.PaymentFrequency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanCalculatorRequest {

    private Double loanAmount;
    private Double interestRate;
    private Integer numberOfPayments;
    private PaymentFrequency paymentFrequency;
}
