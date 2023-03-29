package gr.kariera.mindthecode.mindthecodefinalproject;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class ProductsOrder {
    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty
    private String email;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "PRODUCTS_ORDER_ID", referencedColumnName = "id", insertable = true, updatable = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public ProductsOrder(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Order[id=%d, customer email ='%s', total price='%d']", id, email, getTotalOrderPrice());
    }

    @Transient
    public BigDecimal getTotalOrderPrice() {
        BigDecimal totalPrice = orderItems.stream()
                .map(x -> (x.getPrice().multiply(BigDecimal.valueOf(x.getQuantity()))))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        return totalPrice;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        orderItems.forEach(i -> i.setPrice(i.getProduct().getProduct_price()));
    }

    public void updateOrderItemsPrice() {
        orderItems.stream()
                .filter((i) -> i.getProduct()!=null)
                .forEach(i -> i.setPrice(i.getProduct().getProduct_price()));
    }
}
