package com.example.spring_loanCalculator.util;

import com.example.spring_loanCalculator.model.PaymentFrequency;

public class Util {
    // simple calculation no 1.
    public static double calculateMonthlyLoanPayment(double loanAmount, double interestRate, int numberOfPayments, PaymentFrequency paymentFrequency) {

        double i = interestRate / 100 / getFrequencyAsNumber(paymentFrequency);

        double payment = (loanAmount * (i * Math.pow(1 + i, numberOfPayments))) /
                (Math.pow(1 + i, numberOfPayments) - 1);
        System.out.println(Math.round(payment * 100.00) / 100.00);
        return Math.round(payment * 100.00) / 100.00;
    }

    // simple calculation no. 2
    public static double calculateTotalAmountPaidWithInterest(double singlePayment, int numberOfMonths) {
        System.out.println(Math.round(singlePayment * numberOfMonths * 100.00) / 100.00);
        return Math.round(singlePayment * numberOfMonths * 100.00) / 100.00;
    }

    // simple calculation no. 3
    public static double calculateTotalInterestPaid(double totalAmount, double loanAmount) {
        System.out.println(Math.round((totalAmount - loanAmount) * 100.0) / 100.0);
        return Math.round((totalAmount - loanAmount) * 100.0) / 100.0;
    }

    public static int getFrequencyAsNumber(PaymentFrequency paymentFrequency) {
        // return payments per year

        int frequencyPerYear = -1;

        switch (paymentFrequency) {
            case DAILY:
                frequencyPerYear = 365;
                break;
            case WEEKLY:
                frequencyPerYear = 52;
                break;
            case BIWEEKLY:
                frequencyPerYear = 26;
                break;
            case MONTHLY:
                frequencyPerYear = 12;
                break;
            case SEMI_MONTH:
                frequencyPerYear = 24;
                break;
            case BIMONTHLY:
                frequencyPerYear = 6;
                break;
            case QUARTERLY:
                frequencyPerYear = 4;
                break;
            case SEMI_ANNUAL:
                frequencyPerYear = 2;
                break;
            case ANNUAL:
                frequencyPerYear = 1;
                break;
            default:
                frequencyPerYear = -1;
                break;


        }
        return frequencyPerYear;
    }
}
