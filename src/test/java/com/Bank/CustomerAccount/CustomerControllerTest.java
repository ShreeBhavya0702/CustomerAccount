package com.Bank.CustomerAccount;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.Bank.CustomerAccount.Controller.CustomerController;
import com.Bank.CustomerAccount.Entity.CustomerData;
import com.Bank.CustomerAccount.Service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Date;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(value = CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetCustomerById() throws Exception {
        Date date = Date.valueOf("1994-02-07");

        CustomerData mockCustomer = new CustomerData();
        mockCustomer.setId(10);
        mockCustomer.setCustomerName("Bhavya");
        mockCustomer.setDob(date);
        mockCustomer.setGender("Female");
        mockCustomer.setPhoneNumber(987654L);
        mockCustomer.setAddress("Bengaluru");
        mockCustomer.setEmailId("bhavyashree@gmail.com");
        mockCustomer.setPanCard("Bha1234vya");

        // Mockito.when(customerService.findById(Mockito.anyInt())).thenReturn(Optional.of(mockCustomer));
        int id = 10;
        given(customerService.findById(id)).willReturn(Optional.of(mockCustomer));

        ResultActions response = mockMvc.perform((RequestBuilder) get("/customer/getCustomerById?id="+id));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(mockCustomer.getCustomerName())));
    }
}