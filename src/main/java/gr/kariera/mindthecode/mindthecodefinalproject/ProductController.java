package gr.kariera.mindthecode.mindthecodefinalproject;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "View a list of available products", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list")})

    @GetMapping
    ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    };

    @ApiOperation(value = "Get the product given its product id ", response = Product.class)
    @GetMapping("/{id}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Product id not found")

    })
    public ResponseEntity<Object> getProduct(
            @ApiParam(value = "Product id", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @ApiOperation(value = "Create a new product ", response = Product.class)
    @PostMapping
    public ResponseEntity<Object> createProduct(
            @ApiParam(value = "Product object to store in database table", required = true) @RequestBody Product product) {
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
    @ApiOperation(value = "Update an product")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully update"),
            @ApiResponse(code = 404, message = "Product id not found") })
    public ResponseEntity<Object> updateProduct(
            @ApiParam(value = "Product object to store in database table", required = true) @RequestBody Product product,
            @ApiParam(value = "Product id", required = true) @PathVariable Long id) {
        product.setId(id);
        try {
            productService.updateProduct(product);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Invalid order id " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete a product given its product id")
    public void deleteProduct(@ApiParam(value = "Product id", required = true) @PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
