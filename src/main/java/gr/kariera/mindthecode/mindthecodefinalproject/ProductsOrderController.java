package gr.kariera.mindthecode.mindthecodefinalproject;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class ProductsOrderController {

    @Autowired
    private ProductsOrderService orderService;

    @GetMapping
    ResponseEntity<List<ProductsOrder>> getAllOrders() {
        return ResponseEntity.ok( orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsOrder> getOrder(@Min(1) @PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PostMapping
    public ResponseEntity<Object> createOrder(@Valid @RequestBody ProductsOrder order) {
        ProductsOrder savedOrder = orderService.addOrder(order);
        if(savedOrder != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedOrder.getId()).toUri();
            return ResponseEntity.created(location).build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateOrder(@Valid @RequestBody ProductsOrder order, @PathVariable Integer id) {
        order.setId(id);
        orderService.updateOrder(order);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOrder(@Min(1) @PathVariable Integer id) {
        orderService.deleteOrder(id);
    }
}
