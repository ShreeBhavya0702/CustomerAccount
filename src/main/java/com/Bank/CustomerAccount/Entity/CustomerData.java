package com.Bank.CustomerAccount.Entity;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Persistence is a class that contains static methods to obtain an EntityManagerFactory instance.
import javax.persistence.*;
import java.sql.Date;

//@Data is a convenient shortcut annotation that bundles the features of @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor together
@Data
//@AllArgsConstructor annotation generates a constructor with one parameter for every field in the class.
@AllArgsConstructor
//@NoArgsConstructor annotation is used to generate the no-argument constructor for a class.
@NoArgsConstructor
//@Entity is persistence object that stores record into database
@Entity
//@Table allows to specify the details of the table
@Table(name = "Customer")
public class CustomerData
{
    //@Id specifies the primary key of entity
    @Id
    //@GeneratedValue will automatically generate primary key value
    @GeneratedValue(strategy = GenerationType.AUTO)

    //Initialising the variables and datatype.
    int id;
    String CustomerName;
    Date Dob;
    String Gender;
    long PhoneNumber;
    String Address;
    String EmailId;
    String PanCard;

}