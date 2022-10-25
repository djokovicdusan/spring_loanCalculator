package com.example.spring_loanCalculator.repository;

import com.example.spring_loanCalculator.model.LoanDetailsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDetailsRequestRepository extends JpaRepository<LoanDetailsRequest, Long> {
}
