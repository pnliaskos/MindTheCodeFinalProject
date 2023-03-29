package gr.kariera.mindthecode.mindthecodefinalproject;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    };


    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@Min(1) @PathVariable Integer id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@Valid @RequestBody Product product) {
        Product savedProduct = productService.addProduct(product);
        if(savedProduct != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedProduct.getId()).toUri();
            return ResponseEntity.created(location).build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateProduct(@Valid @RequestBody Product product, @Min(1) @PathVariable Integer id) {
        product.setId(id);
        try {
            productService.updateProduct(product);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Invalid order id " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@Min(1) @PathVariable Integer id) {
        productService.deleteProduct(id);
    }

}
