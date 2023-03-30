package gr.kariera.mindthecode.mindthecodefinalproject.Repositories;


import gr.kariera.mindthecode.mindthecodefinalproject.Entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    Page<Order> findByAddressContainingIgnoreCase(String address, Pageable pageable);
}
