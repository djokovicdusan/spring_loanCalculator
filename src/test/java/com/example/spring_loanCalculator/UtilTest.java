package com.example.spring_loanCalculator;

import com.example.spring_loanCalculator.model.LoanDetails;
import com.example.spring_loanCalculator.model.PaymentFrequency;
import com.example.spring_loanCalculator.util.Util;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UtilTest {

    @Test
    public void calculateMonthlyLoanPaymentTest() {
        LoanDetails loanDetails = LoanDetails.builder().loanAmount(25000.00)
                .interestRate(5.00)
                .numberOfPayments(12)
                .paymentFrequency(PaymentFrequency.MONTHLY)
                .build();
        assertEquals(Util.calculateMonthlyLoanPayment(loanDetails.getLoanAmount(), loanDetails.getInterestRate(),
                loanDetails.getNumberOfPayments(), loanDetails.getPaymentFrequency()), 2140.19);
    }

    @Test
    public void calculateTotalAmountPaidWithInterestTest() {
        double monthlyPayment = 2000;
        int numberOfMonths = 6;
        assertThat(Util.calculateTotalAmountPaidWithInterest(monthlyPayment, numberOfMonths)).isEqualTo(12000.00);
    }

    @Test
    public void calculateTotalInterestPaidTest() {
        double totalAmountPaidWithInterest = 25682.28;
        double loanAmount = 25000.00;
        assertEquals(Util.calculateTotalInterestPaid(totalAmountPaidWithInterest, loanAmount), 682.28);
    }

    @Test
    public void getFrequencyAsNumberTest() {
        PaymentFrequency paymentFrequency = PaymentFrequency.ANNUAL;
        assertThat(Util.getFrequencyAsNumber(PaymentFrequency.ANNUAL)).isEqualTo(1);
    }

}
