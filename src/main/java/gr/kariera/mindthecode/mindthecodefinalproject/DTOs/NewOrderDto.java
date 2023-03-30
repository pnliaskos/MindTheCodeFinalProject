package gr.kariera.mindthecode.mindthecodefinalproject.DTOs;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class NewOrderDto implements Serializable {
    private String address;

    private Collection<ProductWithQuantityDto> products;

    public NewOrderDto() {
        this.products = new ArrayList<>();
    }

}
