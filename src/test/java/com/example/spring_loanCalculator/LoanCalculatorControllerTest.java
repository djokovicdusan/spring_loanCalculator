package com.example.spring_loanCalculator;

import com.example.spring_loanCalculator.model.LoanDetails;
import com.example.spring_loanCalculator.model.PaymentFrequency;
import com.example.spring_loanCalculator.repository.LoanCalculatorRepository;
import com.example.spring_loanCalculator.service.LoanCalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import java.nio.charset.Charset;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class LoanCalculatorControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private LoanCalculatorService loanCalculatorService;
    private LoanCalculatorRepository loanCalculatorRepository;
    private LoanDetails loanDetails;
    private LoanDetails loanDetailsBad;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        loanCalculatorRepository = Mockito.mock(LoanCalculatorRepository.class);
        loanCalculatorService = new LoanCalculatorService(loanCalculatorRepository);
        loanDetails = LoanDetails.builder().loanAmount(25000.00)
                .interestRate(5.00)
                .numberOfPayments(12)
                .paymentFrequency(PaymentFrequency.MONTHLY)
                .build();
        loanDetailsBad = LoanDetails.builder().loanAmount(25000.00)
                .paymentFrequency(PaymentFrequency.MONTHLY)
                .build();
    }

    @Test
    public void loanDetailsBadRequestTest() throws Exception {
        String url = "/api/loanCalculator";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer();
        String requestJson = ow.writeValueAsString(null);

        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loanDetailsTest() throws Exception {
        String url = "/api/loanCalculator";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer();
        String requestJson = ow.writeValueAsString(loanDetails);

        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isCreated());
    }
}
