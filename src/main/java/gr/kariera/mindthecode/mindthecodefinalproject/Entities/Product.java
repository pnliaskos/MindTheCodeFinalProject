package gr.kariera.mindthecode.mindthecodefinalproject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String category;
    private BigDecimal price;
    private String size;
    private String color;
    private String imagePath;
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private Collection<OrderProduct> orderProducts = new ArrayList<>();

}
