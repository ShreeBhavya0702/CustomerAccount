package com.Bank.CustomerAccount.Controller;

import com.Bank.CustomerAccount.Entity.CustomerData;
import com.Bank.CustomerAccount.Service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    private final static Logger logger = Logger.getLogger(CustomerService.class);
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/default")
    public String defaultMethod()
    {
        logger.info("Default page");
        String message = "\nThis method is used for displaying Customer page";
        FileCreation(message);
        return "This is Customer Details Page";
    }

    //This Method is used for adding customer
    @PostMapping(value = "/addCustomer")
    public String addCreditCardDetails(@RequestBody CustomerData customerData)
    {
        logger.info("Added new credit card details " + customerData);
        String message = "\nThis method is used for adding new credit card";
        FileCreation(message);
        customerService.addCustomer(customerData);
        return "Customer details added successfully";
    }

    //This Method is used for getting details of all the customers
    @GetMapping("/allCustomers")
    public List<CustomerData> findAllCustomers()
    {
        logger.info("Getting details of all the Customers ");
        String message = "\nThis method is used for getting record of all the customers";
        FileCreation(message);
        return customerService.getCustomers();
    }

    //This Method is used for getting customer by specifying the id.
    @GetMapping(value = "/getCustomerById/{id}")
    public Optional<CustomerData> findById(@PathVariable int id)
    {
        logger.info("Get customer details " + id);
        String message = "\nThis method is used for getting customer details by id";
        FileCreation(message);
        return customerService.getCustomerById(id);
    }

    /*@GetMapping("/customerByName/{customerName}")
    public CustomerData getCustomerByName(@PathVariable String customerName)
    {
        String message = "This method is used to get the customer by name";
        FileCreation(message);
        return customerService.getCustomerByName(customerName);
    }*/

    //This Method is used for updated or altering changes to the existing customer details.
    @PutMapping(value = "/updateCustomer")
    public String updateCustomerDetails(@RequestBody CustomerData customerData) {

        logger.info("Update Customer Details " + customerData);
        String message = "\nThis method is used for updating the customer details";
        FileCreation(message);
        boolean customerPresent = customerService.updateCustomer(customerData);
        if (customerPresent)
            return "Customer Details Updated Successfully";
        else
            return "Customer not found";
    }

    //This Method is used for finding customer details by specifying the customer name
    @GetMapping("/customerByName/{customerName}")
    public List<CustomerData> getAccountByAccountHolderName(@PathVariable String customerName) {
        logger.info("Get Customer Details by name " +customerName);
        String message = "\nThis method is used to get customer details by specifying the customer name";
        FileCreation(message);
        return customerService.getCustomerByCustomerName(customerName);
    }

    //This Method is used for deleting customer details by specifying the id
    @DeleteMapping(value = "/deleteCustomerById/{id}")
    public String deleteCustomerById(@PathVariable int id)
    {
        logger.info("Customer deleted " + id);
        String message = "\nThis method is used for deleting customer by id";
        FileCreation(message);
        return customerService.deleteCustomerById(id);
    }

    //This Method is used for creating and writing file
    public void FileCreation(String message)
    {
        try
        {
            File myObj = new File("F:\\FileProject\\CustomerDetails.txt");
            myObj.createNewFile() ;
            System.out.println("File created: " + myObj.getName());
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try
        {
            FileWriter myWriter = new FileWriter("F:\\FileProject\\CustomerDetails.txt",true);
            myWriter.write(message+ System.lineSeparator());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //This Method is used for reading logs
    @GetMapping(value = "/readLogs")
    String readFile()
    {
        try
        {
            File myObj = new File("F:\\FileProject\\CustomerDetails.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                System.out.println(data);
                return data;
            }
            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}




