package com.example.spring_loanCalculator;

import com.example.spring_loanCalculator.model.AmortizationDetails;
import com.example.spring_loanCalculator.model.LoanDetails;
import com.example.spring_loanCalculator.model.PaymentDetails;
import com.example.spring_loanCalculator.model.PaymentFrequency;
import com.example.spring_loanCalculator.repository.LoanCalculatorRepository;
import com.example.spring_loanCalculator.util.Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class LoanCalculatorRepositoryTest {
    @Autowired
    private LoanCalculatorRepository loanCalculatorRepository;

    @Test
    public void saveLoanDetailsTest(){
        LoanDetails loanDetails = LoanDetails.builder().loanAmount(25000.00)
                .interestRate(5.00)
                .numberOfPayments(12)
                .paymentFrequency(PaymentFrequency.MONTHLY)
                .build();
        double monthlyLoanPayment = Util.calculateMonthlyLoanPayment(loanDetails.getLoanAmount(),
                loanDetails.getInterestRate(),
                loanDetails.getNumberOfPayments(),
                loanDetails.getPaymentFrequency());
        double totalAmountPaidWithInterest = Util.calculateTotalAmountPaidWithInterest(monthlyLoanPayment, loanDetails.getNumberOfPayments());
        double totalInterestPaid = Util.calculateTotalInterestPaid(totalAmountPaidWithInterest, loanDetails.getLoanAmount());
        PaymentDetails paymentDetails = new PaymentDetails(monthlyLoanPayment, totalAmountPaidWithInterest, totalInterestPaid);

        loanDetails.setPaymentDetails(paymentDetails);
        double balanceOwed = loanDetails.getLoanAmount();
        List<AmortizationDetails> amortizationDetailsList = new ArrayList<>();
        for (int i = 0; i < Util.getFrequencyAsNumber(loanDetails.getPaymentFrequency()); i++) {
            double interestAmount = Math.round(balanceOwed * loanDetails.getInterestRate() / 100 /
                    Util.getFrequencyAsNumber(loanDetails.getPaymentFrequency()) * 100.00) / 100.00;
            double principalAmount = Math.round((monthlyLoanPayment - interestAmount) * 100.00) / 100.00;
            balanceOwed -= principalAmount;
            balanceOwed = Math.round((balanceOwed) * 100.00) / 100.00;
            if (i + 1 == Util.getFrequencyAsNumber(loanDetails.getPaymentFrequency())) {
                balanceOwed = 0;
            }
            AmortizationDetails singlePaymentAmortizationDetails = new AmortizationDetails(i + 1, monthlyLoanPayment, principalAmount, interestAmount, balanceOwed);
            amortizationDetailsList.add(singlePaymentAmortizationDetails);
        }
        loanDetails.setPayments(amortizationDetailsList);

        loanDetails = this.loanCalculatorRepository.save(loanDetails);

        assertThat(loanDetails.getId()).isGreaterThan(0);
    }

}
