package com.example.spring_loanCalculator.controller;

import com.example.spring_loanCalculator.service.ILoanCalculatorService;
import com.example.spring_loanCalculator.transfer.LoanCalculatorRequest;
import com.example.spring_loanCalculator.transfer.LoanCalculatorResponse;
import com.example.spring_loanCalculator.util.CustomAlert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/loanCalculator")
public class LoanCalculatorController {

    private final ILoanCalculatorService iLoanCalculatorService;

    public LoanCalculatorController(ILoanCalculatorService iLoanCalculatorService) {
        this.iLoanCalculatorService = iLoanCalculatorService;
    }

    @PostMapping()
    public ResponseEntity<?> saveLoanDetails(@RequestBody LoanCalculatorRequest loanCalculatorRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("error binding");
            return new ResponseEntity<>(new CustomAlert("error", "error"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(this.iLoanCalculatorService.saveLoanDetailsRequest(loanCalculatorRequest), HttpStatus.CREATED);
    }
}
