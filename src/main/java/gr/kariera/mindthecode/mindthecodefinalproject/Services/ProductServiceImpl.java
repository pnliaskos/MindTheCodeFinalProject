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
import java.util.Optional;

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
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    ) {
        Optional<Integer> pageOptional = Optional.ofNullable(page);
        int pageNumber = pageOptional.orElse(0);

        Optional<Integer> sizeOptional = Optional.ofNullable(size);
        int sizeNumber = sizeOptional.orElse(3);

        Optional<String> sortOptional = Optional.ofNullable(sort);
        String sortOrder = sortOptional.orElse("ASC");

        PageRequest paging = PageRequest
                .of(pageNumber, sizeNumber)
                .withSort(sortOrder.equalsIgnoreCase("ASC") ?
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
