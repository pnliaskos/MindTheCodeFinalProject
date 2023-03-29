package gr.kariera.mindthecode.mindthecodefinalproject;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(notes = "The auto-generated id of the product")
    private Long id;

    @Column(nullable=false)
    @ApiModelProperty(notes = "The product name")
    private String name;

    /**
     * Product price
     */
    @Column(nullable=false)
    @ApiModelProperty(notes = "The product price")
    private Double price = new Double(0.0);

    /**
     * No args constructor
     */
    public Product() {

    }

    /**
     * Constructor of a product given its name and price
     * @param name the product name
     * @param price the product price
     */
    public Product(String name, Double price) {
        super();
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, name='%s', price='%d']",
                id, name, price);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }



}
