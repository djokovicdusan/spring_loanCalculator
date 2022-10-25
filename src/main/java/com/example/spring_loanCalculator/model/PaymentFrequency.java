package com.example.spring_loanCalculator.model;

public enum PaymentFrequency {
    // this could have been implemented with final int  fields, since it will never change
    DAILY,
    WEEKLY,
    BIWEEKLY,
    SEMI_MONTH,
    MONTHLY,
    BIMONTHLY,
    QUARTERLY,
    SEMI_ANNUAL,
    ANNUAL;
}
