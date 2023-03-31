package gr.kariera.mindthecode.mindthecodefinalproject.DTOs;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductWithQuantityDto implements Serializable {

    private Integer id;
    private String title;
    private Integer quantity;

}
