package gr.kariera.mindthecode.mindthecodefinalproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Order {
    @GeneratedValue
    @Id
    private Integer order_id;
    private String order_status;



}
