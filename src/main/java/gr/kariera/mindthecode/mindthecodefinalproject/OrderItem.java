package gr.kariera.mindthecode.mindthecodefinalproject;


import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @ApiModelProperty(notes = "The product that has been ordered ")
    private Product product;

    @Column(nullable=false)
    @NotNull
    @ApiModelProperty(notes = "The quantity of the product that has been ordered ")
    private Short quantity = new Short((short) 0);

    @Column(nullable=false)
    @NotNull
    @ApiModelProperty(notes = "The current product price ")
    private Double price = new Double(0.0);

    @ManyToOne
    private ProductsOrder productsOrder;

    public OrderItem() {

    }
    public OrderItem(Product product, Short quantity) {
        super();
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice();
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
        price = product.getPrice();
    }
    public Short getQuantity() {
        return quantity;
    }
    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }
    public Double getPrice() {
        if (price == null) {
            price = product.getPrice();
        }
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
