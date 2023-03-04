package com.Bank.CustomerAccount;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.Bank.CustomerAccount.Controller.CustomerController;
import com.Bank.CustomerAccount.Entity.CustomerData;
import com.Bank.CustomerAccount.Repository.CustomerRepository;
import com.Bank.CustomerAccount.Service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void testGetCustomerById() throws Exception {
        CustomerData mockCustomer = new CustomerData();
        mockCustomer.setId(1);
        mockCustomer.setCustomerName("Bhavya");
        mockCustomer.setDob(Date.valueOf("1994-02-07"));
        mockCustomer.setGender("Female");
        mockCustomer.setPhoneNumber(987654L);
        mockCustomer.setAddress("Bengaluru");
        mockCustomer.setEmailId("bhavyashree@gmail.com");
        mockCustomer.setPanCard("Bha1234vya");

        // Mockito.when(customerService.findById(Mockito.anyInt())).thenReturn(Optional.of(mockCustomer));
        int id = 1;
        given(customerService.findById(id)).willReturn(Optional.of(mockCustomer));

        ResultActions response = mockMvc.perform((RequestBuilder) get("/customer/getCustomerById?id=" + id));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(mockCustomer.getCustomerName())));

    }

    @Test
    public void testGetCustomerByIdNegative() throws Exception {
        int id = 1;
        given(customerService.findById(id)).willReturn(null);
        ResultActions response = mockMvc.perform((RequestBuilder) get("/customer/getCustomerById?id=" + id));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void testGetAllCustomersDetails() throws Exception {
        List<CustomerData> customers = new ArrayList<>();
        customers.add(new CustomerData(1, "Bhavya", Date.valueOf("1994-02-07"), "Female", 987654L, "Bengaluru", "bhavyashree@gmail.com", "bha1234vya"));
        customers.add(new CustomerData(2, "Bhavya", Date.valueOf("1994-02-07"), "Female", 987654L, "Bengaluru", "bhavyashree@gmail.com", "bha1234vya"));

        //customers.add(new CustomerData(2, "Cust1", "1994-02-07", "Female", 987654L, "Bengaluru", Date.valueOf("1994-02-07"), "bha1234vya"));
        //customers.add(new CustomerData(2, "Cust2", 99001122L, "Street1", "dumm1@gmail.com", "qwerty12", Date.valueOf("1993-10-09"), "Male"));
        given(customerService.getCustomers()).willReturn(customers);
        ResultActions response = mockMvc.perform(get("/customer/getCustomers"));
        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(customers.size())));
    }

    /*@Test
    public void testAddCustomerDetails() throws Exception {

        Date date = Date.valueOf("1994-02-07");
        CustomerData mockCustomer = new CustomerData();
        mockCustomer.setId(1);
        mockCustomer.setCustomerName("Bhavya");
        mockCustomer.setDob(date);
        mockCustomer.setGender("Female");
        mockCustomer.setPhoneNumber(987654L);
        mockCustomer.setAddress("Bengaluru");
        mockCustomer.setEmailId("bhavyashree@gmail.com");
        mockCustomer.setPanCard("Bha1234vya");

        //given(customerService.addCustomer(any(Customer.class))).willReturn(mockCustomer);

        Mockito.when(customerService.addCustomer(any(CustomerData.class))).thenReturn(mockCustomer);

        ResultActions response = mockMvc.perform(post("/customer/addCustomer")
                .content(asJsonString(mockCustomer))
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$").isString())
                .andDo(print());
    }*/

    @Test
    public void testUpdateCustomer() throws Exception {

        boolean status = true;
        CustomerData mockCustomer = new CustomerData(1, "Bhavya", Date.valueOf("1994-02-07"), "Female", 987654L, "Bengaluru", "bhavyashree@gmail.com", "bha1234vya");
                //(1, "Cust1", 99001122L, "Street1", "city", "dumm1@gmail.com", "qwerty12", "1234567", Date.valueOf("1993-10-09"), "Male");

        //given(customerService.updateCustomerDetails(mockCustomer)).willReturn(true);
        given(customerService.findById(anyInt())).willReturn(Optional.of(mockCustomer));
        given(customerService.updateCustomer(any(CustomerData.class))).willReturn(true);

        ResultActions response = mockMvc.perform(put("/customer/updateCustomer").content(asJsonString(mockCustomer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isString())
                .andDo(print());
    }

    @Test
    public void testUpdateCustomerDetails_false() throws Exception
    {
        boolean status = true;
        CustomerData mockCustomer = new CustomerData(1, "Bhavya", Date.valueOf("1994-02-07"), "Female", 987654L, "Bengaluru", "bhavyashree@gmail.com", "bha1234vya");
        //given(customerService.updateCustomerDetails(mockCustomer)).willReturn(true);
        given(customerService.findById(anyInt())).willReturn(Optional.of(mockCustomer));
        given(customerService.updateCustomer(any(CustomerData.class)))
                .willReturn(false);
        ResultActions response = mockMvc.perform(put("/customer/updateCustomer")
                        .content(asJsonString(mockCustomer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isString())
                .andDo(print());
    }

    public static String asJsonString(final Object obj)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}

