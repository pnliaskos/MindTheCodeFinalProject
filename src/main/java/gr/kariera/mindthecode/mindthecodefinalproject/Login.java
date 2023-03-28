package gr.kariera.mindthecode.mindthecodefinalproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Entity
public class Login {
    @GeneratedValue
    int login_id;

    private String userEmail;

    private String userPassword;

}
