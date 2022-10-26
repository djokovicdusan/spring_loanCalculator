package com.example.spring_loanCalculator.service;

import com.example.spring_loanCalculator.model.LoanDetails;
import com.example.spring_loanCalculator.transfer.LoanCalculatorRequest;
import com.example.spring_loanCalculator.transfer.LoanCalculatorResponse;

public interface ILoanCalculatorService {

    LoanCalculatorResponse saveLoanDetailsRequest(LoanCalculatorRequest loanCalculatorRequest);


}
