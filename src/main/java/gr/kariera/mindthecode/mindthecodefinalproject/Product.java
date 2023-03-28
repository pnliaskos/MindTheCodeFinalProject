package gr.kariera.mindthecode.mindthecodefinalproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    private Integer product_id;
    private String product_title;
    private String product_category;
    private BigDecimal product_price;
    private String product_size;
    private String product_color;

    // Used for large object like images
    @Lob
    private byte[] image;
    private String product_description;

}
