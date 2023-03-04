package com.Bank.CustomerAccount;

import com.Bank.CustomerAccount.Entity.CustomerData;
import com.Bank.CustomerAccount.Repository.CustomerRepository;
import com.Bank.CustomerAccount.Service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@WebMvcTest(value = CustomerService.class)
public class CustomerServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    CustomerData mockCustomer = new CustomerData();

    @BeforeEach
    public void setup()
    {
        mockCustomer.setId(10);
        mockCustomer.setCustomerName("Bhavya");
        mockCustomer.setDob(Date.valueOf("1994-02-07"));
        mockCustomer.setGender("Female");
        mockCustomer.setPhoneNumber(987654L);
        mockCustomer.setAddress("Halasuru");
        mockCustomer.setEmailId("bhavyashree@gmail.com");
        mockCustomer.setPanCard("Bha1234vya");
    }

    @Test
    public void testFindById()
    {
        given(customerRepository.findById(Mockito.anyInt())).willReturn(Optional.of(mockCustomer));
        Optional<CustomerData> savedCustomer = customerService.findById(10);
        assert(savedCustomer).isPresent();
    }
}