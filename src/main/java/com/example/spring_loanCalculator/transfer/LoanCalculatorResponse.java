package com.example.spring_loanCalculator.transfer;

import com.example.spring_loanCalculator.model.AmortizationDetails;
import com.example.spring_loanCalculator.model.PaymentDetails;
import com.example.spring_loanCalculator.model.PaymentFrequency;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class LoanCalculatorResponse implements Serializable {
    private Long id;
    private Double loanAmount;
    private Double interestRate;
    private Integer numberOfPayments;
    private PaymentFrequency paymentFrequency;
    private List<AmortizationDetails> amortizationDetailsList;
    private PaymentDetails paymentDetails;
}
