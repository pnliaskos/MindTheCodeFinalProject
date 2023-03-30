package gr.kariera.mindthecode.mindthecodefinalproject.Controllers;

import gr.kariera.mindthecode.mindthecodefinalproject.DTOs.ProductDto;
import gr.kariera.mindthecode.mindthecodefinalproject.Entities.Product;
import gr.kariera.mindthecode.mindthecodefinalproject.Services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping(path = "/api")
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

    @GetMapping("/products")
    public Page<Product> all(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    ) {
        return service.getProducts(title, page, size, sort);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteProduct(id);
    }
}
