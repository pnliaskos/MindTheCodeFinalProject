package gr.kariera.mindthecode.mindthecodefinalproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

@Data
@Entity
public class Order {
    @GeneratedValue
    private int o_id;

    private String status;


}
