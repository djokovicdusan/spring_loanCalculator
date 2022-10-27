package com.example.spring_loanCalculator;

import com.example.spring_loanCalculator.repository.LoanCalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoanCalculatorRepositoryTest {
    @Autowired
    private LoanCalculatorRepository loanCalculatorRepository;
}
