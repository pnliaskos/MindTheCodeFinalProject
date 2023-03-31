package gr.kariera.mindthecode.mindthecodefinalproject.DTOs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    Integer id;
    String title;
    BigDecimal price;
    String category;
    String imagePath;
}
