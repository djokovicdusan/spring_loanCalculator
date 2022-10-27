package com.example.spring_loanCalculator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "amortization_details")
@Data
@NoArgsConstructor


public class AmortizationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "period")
    private int period;

    @Column(name = "payment_amount")
    private double paymentAmount;

    @Column(name = "principal_amount")
    private double principalAmount;

    @Column(name = "interest_amount")
    private double interestAmount;

    @Column(name = "balance_owed")
    private double balanceOwed;


    public AmortizationDetails(int period, double paymentAmount, double principalAmount, double interestAmount, double balanceOwed) {
        this.period = period;
        this.paymentAmount = paymentAmount;
        this.principalAmount = principalAmount;
        this.interestAmount = interestAmount;
        this.balanceOwed = balanceOwed;
    }
}
