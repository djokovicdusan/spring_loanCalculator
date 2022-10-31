package com.example.spring_loanCalculator.service;

import com.example.spring_loanCalculator.mapper.Mapper;
import com.example.spring_loanCalculator.model.AmortizationDetails;
import com.example.spring_loanCalculator.model.LoanDetails;
import com.example.spring_loanCalculator.model.PaymentDetails;
import com.example.spring_loanCalculator.repository.LoanCalculatorRepository;
import com.example.spring_loanCalculator.transfer.LoanCalculatorRequest;
import com.example.spring_loanCalculator.transfer.LoanCalculatorResponse;
import com.example.spring_loanCalculator.util.Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanCalculatorService implements ILoanCalculatorService {
    private final LoanCalculatorRepository loanCalculatorRepository;

    public LoanCalculatorService(LoanCalculatorRepository loanCalculatorRepository) {
        this.loanCalculatorRepository = loanCalculatorRepository;
    }

    @Override
    public LoanCalculatorResponse saveLoanDetailsRequest(LoanCalculatorRequest loanCalculatorRequest) {

// maybe move this code somewhere

        LoanDetails loanDetails = Mapper.getInstance().mapToEntity(loanCalculatorRequest);

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

//        LoanDetails loanDetailsDummy = this.loanCalculatorRepository.save(loanDetails);


        return Mapper.getInstance().mapToDTO(this.loanCalculatorRepository.save(loanDetails));
    }
}
