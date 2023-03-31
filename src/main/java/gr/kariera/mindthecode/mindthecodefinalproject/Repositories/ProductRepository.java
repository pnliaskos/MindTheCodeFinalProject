package gr.kariera.mindthecode.mindthecodefinalproject.Repositories;

import gr.kariera.mindthecode.mindthecodefinalproject.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByPrice(BigDecimal price, Pageable pageable);
    List<Product> findByTitle(String title);
    Page<Product> findAll(Pageable pageable);


}
