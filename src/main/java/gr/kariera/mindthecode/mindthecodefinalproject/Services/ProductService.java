package gr.kariera.mindthecode.mindthecodefinalproject.Services;

import gr.kariera.mindthecode.mindthecodefinalproject.DTOs.ProductDto;
import gr.kariera.mindthecode.mindthecodefinalproject.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    public abstract Product createOrUpdateProduct(Integer id, Product product) throws Exception;
    public abstract void deleteProduct(Integer id);


    public abstract Product getById(Integer id);
    public abstract List<Product> getByTitle(String title);

    Page<ProductDto> getAllProducts(BigDecimal price, Integer page, Integer size, String sort);
}
