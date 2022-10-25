package com.example.spring_loanCalculator.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "payment_details")
@NoArgsConstructor
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "monthly_payment")
    private double monthlyPayment;

    @Column(name = "total_amount_paid_with_interest")
    private double totalAmountPaidWithInterest;

    @Column(name = "total_interest_paid")
    private double totalInterestPaid;

    public PaymentDetails(double monthlyPayment, double totalAmountPaidWithInterest, double totalInterestPaid) { // without id, relationship-wise
        this.monthlyPayment = monthlyPayment;
        this.totalAmountPaidWithInterest = totalAmountPaidWithInterest;
        this.totalInterestPaid = totalInterestPaid;
    }
}
