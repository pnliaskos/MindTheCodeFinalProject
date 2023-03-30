package gr.kariera.mindthecode.mindthecodefinalproject.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

@Entity
@Data
@Table(name = "Order_Product")
public class OrderProduct {
    @EmbeddedId
    private OrderProductPK id;

    @ManyToOne
    @Lazy(false)
    @MapsId("order_id")
    private Order order;

    @ManyToOne
    @Lazy(false)
    @MapsId("product_id")
    private Product product;

    private Integer quantity;
}
