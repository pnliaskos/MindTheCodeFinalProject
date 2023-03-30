package gr.kariera.mindthecode.mindthecodefinalproject.Controllers;

import gr.kariera.mindthecode.mindthecodefinalproject.DTOs.NewOrderDto;
import gr.kariera.mindthecode.mindthecodefinalproject.Entities.Order;
import gr.kariera.mindthecode.mindthecodefinalproject.Services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping(path = "/api")
public class OrderApiController {
    private final OrderService orderService;


    public OrderApiController(OrderService orderService) {
        this.orderService = orderService;
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

    @PostMapping("/orders")
    public Order newOrder(@RequestBody NewOrderDto newOrderDto) {
        try {
            return orderService.newOrder(newOrderDto);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), e.getMessage());
        }
    }

}
