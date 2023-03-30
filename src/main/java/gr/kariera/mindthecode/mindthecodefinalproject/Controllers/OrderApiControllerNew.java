package gr.kariera.mindthecode.mindthecodefinalproject.Controllers;

import gr.kariera.mindthecode.mindthecodefinalproject.Entities.Order;
import gr.kariera.mindthecode.mindthecodefinalproject.Services.OrderService;
import gr.kariera.mindthecode.mindthecodefinalproject.Services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class OrderApiControllerNew {
        private final OrderService orderService;
        private final ProductService productService;

    public OrderApiControllerNew(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/orders/{id}")
    public Order one(@PathVariable Integer id) {
        return orderService.getById(id);

    }

    @GetMapping("/orders")
    public Page<Order> all(
            @RequestParam(required = false) String address,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    ) {
        return orderService.getOrders(address, page, size, sort);
    }

}
