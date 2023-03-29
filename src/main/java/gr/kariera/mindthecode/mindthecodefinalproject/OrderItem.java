package gr.kariera.mindthecode.mindthecodefinalproject;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private BigDecimal price;

    @ManyToOne
    private ProductsOrder productsOrder;

    public OrderItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getProduct_price();
    }
    public void setProduct(Product product) {
        this.product = product;
        price = product.getProduct_price();
    }
    public BigDecimal getPrice() {
        if (price == null) {
            price = product.getProduct_price();
        }
        return price;
    }
}
