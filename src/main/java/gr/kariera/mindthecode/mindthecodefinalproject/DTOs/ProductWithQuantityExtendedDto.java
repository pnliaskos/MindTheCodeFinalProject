package gr.kariera.mindthecode.mindthecodefinalproject.DTOs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductWithQuantityExtendedDto extends ProductWithQuantityDto{
    private BigDecimal price;
    private String description;
}
