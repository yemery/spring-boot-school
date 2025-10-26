package ma.jee.spring.tp3.controller;

import ma.jee.spring.tp3.entity.Category;
import ma.jee.spring.tp3.entity.Product;
import ma.jee.spring.tp3.repository.CategoryRepository;
import ma.jee.spring.tp3.repository.ProductRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository pr, CategoryRepository cr) {
        this.productRepository = pr;
        this.categoryRepository = cr;
    }

    @QueryMapping
    public List<Product> products() {
        return productRepository.findAll();
    }

    @QueryMapping
    public Product product(@Argument Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Product addProduct(@Argument ProductInput input) {
        Category Category = categoryRepository.findById(input.categoryId()).orElse(null);
        Product Product = new Product(input.name(), Float.valueOf(String.valueOf(input.price())), Category);
        return productRepository.save(Product);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument ProductInput input) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(input.name());
        product.setPrice(Float.valueOf(String.valueOf(input.price())));
        Category Category = categoryRepository.findById(input.categoryId()).orElse(null);
        product.setCategory(Category);
        return productRepository.save(product);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        productRepository.deleteById(id);
        return true;
    }

    public record ProductInput(String name, double price, Long categoryId) {}

}
