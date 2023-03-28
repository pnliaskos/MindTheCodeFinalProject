package gr.kariera.mindthecode.mindthecodefinalproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Entity
public class Customer {
    @GeneratedValue
    private int cust_id;

    private String cust_firstName;

    private String cust_lastName;

    private String cust_email;

    private String cust_contactNumber;

    private String cust_address;



}
