package com.Bank.CustomerAccount.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Bank.CustomerAccount.Entity.CustomerData;

import java.util.List;

//@Repository is used for managing data in SpringBoot Application.
@Repository
public interface CustomerRepository extends JpaRepository<CustomerData,Integer>
{
   // List<CustomerData> findByName(String customerName);
}