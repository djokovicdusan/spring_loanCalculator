package com.example.spring_loanCalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // @Entity annotation defines that a class can be mapped to a table.
@Table(name = "loan_details_request")
@Data // @Data is like having implicit @Getter, @Setter, @ToString, @EqualsAndHashCode and @RequiredArgsConstructor

@NoArgsConstructor
@AllArgsConstructor
public class LoanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "loan_amount", nullable = false)
    private Double loanAmount;

    @Column(name = "interest_rate", nullable = false)
    private Double interestRate;

    @Column(name = "number_of_payments", nullable = false)
    private Integer numberOfPayments;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "payment_frequency", nullable = false)
    private PaymentFrequency paymentFrequency;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_details_request_id", referencedColumnName = "id", nullable = false)
    private List<AmortizationDetails> payments = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_details_id", nullable = false)
    private PaymentDetails paymentDetails;
}
