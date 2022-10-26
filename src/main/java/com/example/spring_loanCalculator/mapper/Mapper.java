package com.example.spring_loanCalculator.mapper;

import com.example.spring_loanCalculator.model.LoanDetails;
import com.example.spring_loanCalculator.transfer.LoanCalculatorRequest;
import com.example.spring_loanCalculator.transfer.LoanCalculatorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class Mapper {

    private static ModelMapper modelMapper;
    private static Mapper instance;

    private Mapper() {

    }

    ;

    @Bean
    public static Mapper getInstance() {
        if (instance == null) {
            modelMapper = new ModelMapper();
            return new Mapper();

        }
        return instance;

    }

    public LoanCalculatorResponse mapToDTO(LoanDetails loanDetails) {
        return modelMapper.map(loanDetails, LoanCalculatorResponse.class);
    }

    public LoanDetails mapToEntity(LoanCalculatorRequest loanDetailDto) {
        return modelMapper.map(loanDetailDto, LoanDetails.class);

    }
}
