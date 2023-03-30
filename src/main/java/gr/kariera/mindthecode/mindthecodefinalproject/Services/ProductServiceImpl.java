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

    // Not used
    @Override
    public Page<Product> getProducts(String title, int page, int size, String sort) {
        PageRequest paging = PageRequest
                .of(page, size)
                .withSort(sort.equalsIgnoreCase("ASC") ?
                        Sort.by("title").ascending() :
                        Sort.by("title").descending());

        Page<Product> res;
        if (title == null) {
            res = productRepository.findAll(paging);
        } else {
            res = productRepository.findByTitleContainingIgnoreCase(title, paging);
        }
        return res;
    }

    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(this::convertToDto);
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        productDto.setImagePath(product.getImagePath());
        return productDto;
    }


}
