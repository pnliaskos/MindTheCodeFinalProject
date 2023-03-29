package gr.kariera.mindthecode.mindthecodefinalproject;

//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class ProductsOrderController {

    @Autowired
    private ProductsOrderService orderService;

    //@ApiOperation(value = "View a list of all orders", response = List.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Successfully retrieved list"),
//    })
    @GetMapping
    ResponseEntity<List<ProductsOrder>> getAllOrders() {
        return ResponseEntity.ok( orderService.getAllOrders());
    };

    @GetMapping(params = { "fromDate", "toDate" })
    ResponseEntity<List<ProductsOrder>> getOrdersByTimePeriod(
            @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date fromDate,
            @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date toDate) {
        return ResponseEntity.ok(orderService.getOrdersByTimePeriod(fromDate, toDate));
    };

    //@ApiOperation(value = "Retrieve the order given its product id ", response = ProductsOrder.class)
    @GetMapping("/{id}")
    public ResponseEntity<ProductsOrder> getOrder( @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    //@ApiOperation(value = "Create a new order with all its order items ", response = ProductsOrder.class)
    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody ProductsOrder order) {
        ProductsOrder savedOrder = orderService.addOrder(order);
        if(savedOrder != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedOrder.getId()).toUri();
            return ResponseEntity.created(location).build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    //@ApiOperation(value = "Update an order and its order items")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateOrder( @RequestBody ProductsOrder order, @PathVariable Long id) {
        order.setId(id);
        orderService.updateOrder(order);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    //@ApiOperation(value = "Delete an order given its order id")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

	/*
	@PostMapping(value = "/{id}/orderItems")
	public void addOrderItem(@RequestBody OrderItem orderItem, @PathVariable Long id) {
		ProductsOrder productsOrder = orderService.getOrder(id);
		productsOrder.getOrderItems().add(orderItem);
		orderService.updateOrder(productsOrder);
	}*/

}
