package com.example.spring_loanCalculator.repository;

import com.example.spring_loanCalculator.model.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDetailsRequestRepository extends JpaRepository<LoanDetails, Long> {
}
