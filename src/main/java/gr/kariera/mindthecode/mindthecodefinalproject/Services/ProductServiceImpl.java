package gr.kariera.mindthecode.mindthecodefinalproject.Services;

import gr.kariera.mindthecode.mindthecodefinalproject.DTOs.ProductDto;
import gr.kariera.mindthecode.mindthecodefinalproject.Entities.Product;
import gr.kariera.mindthecode.mindthecodefinalproject.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository repo) {
        this.productRepository = repo;

    }
    @Override
    public Product createOrUpdateProduct(Integer id, Product product) throws Exception {
        if (id != null) {
            if (!id.equals(product.getId())) {
                throw new Exception("id in path does not patch id in body");
            }
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        Product match = productRepository.findById(id)
                .orElseThrow();
        productRepository.delete(match);
    }



    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow();
    }


    @Override
    public Page<ProductDto> getAllProducts(
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    ) {
        PageRequest paging = PageRequest
                .of(page, size)
                .withSort(sort.equalsIgnoreCase("ASC") ?
                        Sort.by("price").ascending() :
                        Sort.by("price").descending());

        Page<Product> products;
        if (price == null) {
            products = productRepository.findAll(paging);
        } else {
            products = productRepository.findByPrice(price, paging);
        }

        return products.map(this::convertToDto);
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        productDto.setImagePath(product.getImagePath());
        return productDto;
    }


}
