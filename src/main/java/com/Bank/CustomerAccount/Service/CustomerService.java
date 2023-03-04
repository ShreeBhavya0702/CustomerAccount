package com.Bank.CustomerAccount.Service;

import com.Bank.CustomerAccount.Entity.CustomerData;
import com.Bank.CustomerAccount.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//@Service allows to add business functionalities
@Service
public class CustomerService
{
    //@Autowired enables to inject the object dependency implicitly
    @Autowired
    private CustomerRepository customerRepository;
    public void addCustomer(CustomerData customerData)
    {
        customerRepository.save(customerData);
    }
    public Optional<CustomerData> getCustomerById(int id)
    {
        return customerRepository.findById(id);
    }

    /*public CustomerData getCustomerByName(String customerName)
    {
        return customerRepository.findByName(customerName);
    }*/

    public List<CustomerData> getCustomers()
    {
        return customerRepository.findAll();
    }

    public CustomerData updateCustomer(CustomerData customerData)
    {
        return customerRepository.save(customerData);
    }

    public String deleteCustomerById(int id) {
        customerRepository.deleteById(id);
        return "Id " + id + " is deleted";
    }

    public Optional<CustomerData> findById(int id) {
        return customerRepository.findById(id);
    }
}
