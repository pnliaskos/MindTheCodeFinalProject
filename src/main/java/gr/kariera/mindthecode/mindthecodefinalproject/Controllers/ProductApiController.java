package gr.kariera.mindthecode.mindthecodefinalproject.Controllers;

import gr.kariera.mindthecode.mindthecodefinalproject.DTOs.ProductDto;
import gr.kariera.mindthecode.mindthecodefinalproject.Entities.Product;
import gr.kariera.mindthecode.mindthecodefinalproject.Services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:8080")
public class ProductApiController {
    private final ProductService service;

    public ProductApiController(ProductService service) {
        this.service = service;
    }

    @PutMapping("/products/{id}")
    public Product update(@PathVariable Integer id, @RequestBody Product product) {
        try {
            return service.createOrUpdateProduct(id, product);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), e.getMessage());
        }
    }

    @PostMapping("/products")
    public Product newProduct(@RequestBody Product product) {
        try {
            return service.createOrUpdateProduct(product.getId(), product);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), e.getMessage());
        }
    }

    @GetMapping("/products/{id}")
    public Product one(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/products/title/{title}")
    public List<Product> byTitle(@PathVariable String title) {
        return service.getByTitle(title);
    }

    @GetMapping("/products")
    public Page<ProductDto> getAllProducts(BigDecimal price, Integer page, Integer size, String sort) {
        return service.getAllProducts(price, page, size, sort);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteProduct(id);
    }
}
