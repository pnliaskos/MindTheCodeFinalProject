package gr.kariera.mindthecode.mindthecodefinalproject.Services;

import gr.kariera.mindthecode.mindthecodefinalproject.DTOs.NewOrderDto;
import gr.kariera.mindthecode.mindthecodefinalproject.Entities.Order;
import gr.kariera.mindthecode.mindthecodefinalproject.Entities.OrderProduct;
import gr.kariera.mindthecode.mindthecodefinalproject.Entities.OrderProductPK;
import gr.kariera.mindthecode.mindthecodefinalproject.Entities.Product;
import gr.kariera.mindthecode.mindthecodefinalproject.Repositories.OrderRepository;
import gr.kariera.mindthecode.mindthecodefinalproject.Repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    public OrderServiceImpl(OrderRepository orderRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    @Override
    public Order newOrder(NewOrderDto newOrder) {
        Order order = new Order();
        order.setAddress(newOrder.getAddress());
        order.setId(newOrder.getId());
        order.setUsername(newOrder.getUsername());
        order.setStatus(newOrder.getStatus());
        order = orderRepo.save(order);
        final Order finalOrder = order;
        newOrder.getProducts()
                .stream()
                .forEach(nop -> {

                    Product p = productRepo
                            .findById(nop.getId())
                            .orElseThrow();
                    OrderProduct op = new OrderProduct();
                    OrderProductPK opPK = new OrderProductPK();
                    opPK.setOrderId(finalOrder.getId());
                    opPK.setProductId(p.getId());
                    op.setId(opPK);
                    op.setOrder(finalOrder);
                    op.setProduct(p);
                    op.setQuantity(nop.getQuantity());
                    finalOrder.getOrderProducts().add(op);
                    finalOrder.setOrderProducts(finalOrder.getOrderProducts());

                });


        order.setTotalCost(order.getTotalCost());
        orderRepo.save(order);

        Order result = orderRepo.findById(order.getId())
                .orElseThrow();
        return result;
    }

    @Override
    public Order update(Integer id, Order order) throws Exception {
        if (id != null) {
            if (!id.equals(order.getId())) {
                throw new Exception("id in path does not patch id in body");
            }
        }
        return orderRepo.save(order);
    }

    @Override
    public Order getById(Integer id) {
        return orderRepo.findById(id)
                .orElseThrow();
    }

    @Override
    public Page<Order> getOrders(String address, int page, int size, String sort) {
        PageRequest paging = PageRequest
                .of(page, size)
                .withSort(sort.equalsIgnoreCase("ASC") ?
                        Sort.by("address").ascending() :
                        Sort.by("address").descending());

        Page<Order> res;
        if (address == null) {
            res = orderRepo.findAll(paging);
        } else {
            res = orderRepo.findByAddressContainingIgnoreCase(address, paging);
        }
        return res;
    }

}
