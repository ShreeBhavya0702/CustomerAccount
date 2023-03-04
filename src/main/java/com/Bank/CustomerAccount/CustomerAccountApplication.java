package com.Bank.CustomerAccount;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication annotation is used to mark a configuration class that declares one or more @Bean methods and also triggers auto-configuration and component scanning.
@SpringBootApplication
public class CustomerAccountApplication
{
	public static void main(String[] args)
	{
		BasicConfigurator.configure();
		SpringApplication.run(CustomerAccountApplication.class, args);
	}
}
