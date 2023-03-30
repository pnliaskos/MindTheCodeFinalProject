package gr.kariera.mindthecode.mindthecodefinalproject.Services;

import gr.kariera.mindthecode.mindthecodefinalproject.DTOs.NewOrderDto;
import gr.kariera.mindthecode.mindthecodefinalproject.Entities.Order;
import org.springframework.data.domain.Page;

public interface OrderService {
    public abstract Order newOrder(NewOrderDto newOrder) throws Exception;

    public abstract Page<Order> getOrders(
            String address,
            int page,
            int size,
            String sort
    );

    public abstract Order getById(Integer id);


}
