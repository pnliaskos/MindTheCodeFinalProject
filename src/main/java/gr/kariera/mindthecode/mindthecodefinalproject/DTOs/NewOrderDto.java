package gr.kariera.mindthecode.mindthecodefinalproject.DTOs;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class NewOrderDto implements Serializable {

    private Integer id;
    private String address;

    private String status;
    private String username;

    private Collection<ProductWithQuantityDto> products;

    public NewOrderDto() {
        this.products = new ArrayList<>();
    }

}
