package gr.kariera.mindthecode.mindthecodefinalproject.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @GeneratedValue
    @Id
    private Integer cust_id;

    private String cust_firstName;

    private String cust_lastName;

    private String cust_email;

    private String cust_contactNumber;

    private String cust_address;
}
