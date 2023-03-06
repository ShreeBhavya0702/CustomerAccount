package com.Bank.CustomerAccount.Service;

import com.Bank.CustomerAccount.Entity.CustomerData;
import com.Bank.CustomerAccount.Repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service allows to add business functionalities
@Service
public class CustomerService
{

    private List<CustomerData> listOfCustomers = new ArrayList<>();

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

    public List<CustomerData> getCustomers()
    {
        return customerRepository.findAll();
    }

    public boolean updateCustomer(CustomerData customerData) {
        Optional<CustomerData> oldCustomer = customerRepository.findById(customerData.getId());
        if(oldCustomer.isPresent()){
            BeanUtils.copyProperties(customerData,oldCustomer);
            return true;
        }
        else{
            return false;
        }
    }

    public String deleteCustomerById(int id) {
        customerRepository.deleteById(id);
        return "Id " + id + " is deleted";
    }

    public Optional<CustomerData> findById(int id) {
        return customerRepository.findById(id);
    }

    public List<CustomerData> getCustomerByCustomerName(String customerName) {
        for (CustomerData customerData : listOfCustomers) {
            if (customerName.equals(customerData.getCustomerName())) {
                return customerRepository.findByCustomerName(customerName);
            }
           // throw new CustomerNotFoundException("Customer not found");

        }
        return null;
    }
}
