package gr.kariera.mindthecode.mindthecodefinalproject;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsOrderService {
    @Autowired
    private ProductsOrderRepository productsOrderRepository;

    List<ProductsOrder> getAllOrders(){
        List<ProductsOrder> orders = new ArrayList<>();
        productsOrderRepository.findAll().forEach(orders::add);
        return orders;
    }

    public ProductsOrder getOrder(Integer id) {
        return productsOrderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }


    public ProductsOrder addOrder(ProductsOrder order) {
        order.updateOrderItemsPrice();
        ProductsOrder savedOrder = productsOrderRepository.save(order);
        return savedOrder;
    }

    public void updateOrder(ProductsOrder order) {
        order.updateOrderItemsPrice();
        productsOrderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        productsOrderRepository.deleteById(id);
    }


}
