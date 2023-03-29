package gr.kariera.mindthecode.mindthecodefinalproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    @NotEmpty(message = "Please provide value!")
    private String product_title;

    @NotEmpty(message = "Please provide value!")
    private String product_category;

    @NotNull(message = "Please provide value!")
    private BigDecimal product_price;

    @NotEmpty(message = "Please provide value!")
    private String product_size;

    @NotEmpty(message = "Please provide value!")
    private String product_color;

    @NotNull(message = "Please provide value!")
    private String product_imageFilePath;
    @NotEmpty(message = "Please provide value!")
    private String product_description;

    public Product(String product_title, String product_category, BigDecimal product_price, String product_size, String product_color, String product_imageFilePath, String product_description) {
        this.product_title = product_title;
        this.product_category = product_category;
        this.product_price = product_price;
        this.product_size = product_size;
        this.product_color = product_color;
        this.product_imageFilePath = product_imageFilePath;
        this.product_description = product_description;
    }
}
