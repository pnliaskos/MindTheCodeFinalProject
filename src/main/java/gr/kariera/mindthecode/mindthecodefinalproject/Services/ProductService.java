package gr.kariera.mindthecode.mindthecodefinalproject.Services;

import gr.kariera.mindthecode.mindthecodefinalproject.DTOs.ProductDto;
import gr.kariera.mindthecode.mindthecodefinalproject.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    public abstract Product createOrUpdateProduct(Integer id, Product product) throws Exception;
    public abstract void deleteProduct(Integer id);
    public abstract Page<Product> getProducts(
            String title,
            int page,
            int size,
            String sort
    );

    public abstract Product getById(Integer id);
    Page<ProductDto> getAllProducts(Pageable pageable);
}
