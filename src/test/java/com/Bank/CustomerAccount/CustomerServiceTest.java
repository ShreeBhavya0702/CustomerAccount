package com.Bank.CustomerAccount;

import com.Bank.CustomerAccount.Entity.CustomerData;
import com.Bank.CustomerAccount.Repository.CustomerRepository;
import com.Bank.CustomerAccount.Service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.sql.Date;
import java.util.ArrayList;
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
    public void setup(){
        mockCustomer.setId(1);
        mockCustomer.setCustomerName("Bhavya");
        mockCustomer.setDob(Date.valueOf("1994-02-07"));
        mockCustomer.setGender("Female");
        mockCustomer.setPhoneNumber(987654L);
        mockCustomer.setAddress("Bengaluru");
        mockCustomer.setEmailId("bhavyashree@gmail.com");
        mockCustomer.setPanCard("Bha1234vya");
    }

    @Test
    public void testFindById(){
        given(customerRepository.findById(Mockito.anyInt())).willReturn(Optional.of(mockCustomer));

        Optional<CustomerData> savedCustomer = customerService.findById(29);

        assert(savedCustomer).isPresent();
    }

    @Test
    public void testFindByIdNegative(){
        given(customerRepository.findById(Mockito.anyInt())).willReturn(null);

        Optional<CustomerData> savedCustomer = customerService.findById(100);

        Assert.isNull(savedCustomer,"Customer not present");
    }

    /*@Test
    public void testAddCustomer(){
        given(customerRepository.save(Mockito.any(CustomerData.class))).willReturn(mockCustomer);

        Optional<CustomerData> savedCustomer  = Optional.ofNullable(customerService.addCustomer(mockCustomer));

        assert (savedCustomer).isPresent();

    }*/

    @Test
    public void testUpdateCustomerDetails(){
        given(customerRepository.findById(1)).willReturn(Optional.ofNullable(mockCustomer));

        boolean status = customerService.updateCustomer(mockCustomer);

        Assert.isTrue(status);

    }

    @Test
    public void testUpdateCustomerDetails_false(){
        given(customerRepository.findById(29)).willReturn(Optional.ofNullable(null));

        boolean status = customerService.updateCustomer(mockCustomer);

        Assert.isTrue(!status);

    }

    @Test
    public void testGetAllCustomers(){
        List<CustomerData> customers = new ArrayList<CustomerData>();
        customers.add(new CustomerData(1, "Bhavya", Date.valueOf("1994-02-07"), "Female", 987654L, "Bengaluru", "bhavyashree@gmail.com", "bha1234vya"));
        customers.add(new CustomerData(2, "Bhavya", Date.valueOf("1994-02-07"), "Female", 987654L, "Bengaluru", "bhavyashree@gmail.com", "bha1234vya"));

        given(customerRepository.findAll()).willReturn(customers);

        List<CustomerData> listOfCustomers = customerService.getCustomers();

        Assert.notEmpty(listOfCustomers);
    }

}