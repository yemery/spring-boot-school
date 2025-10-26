package ma.jee.spring.tp3.service;

import ma.jee.spring.tp3.entity.Product;
import ma.jee.spring.tp3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService implements IService<Product> {

    @Autowired
    private ProductRepository repository;

    @Override
    public Collection<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void insert(Product product) {
        repository.save(product);
    }

    @Override
    public void update(Product product, Long id) {
        Product p = repository.findById(id).orElse(null);
        if (p != null){
            p.setCategory(product.getCategory());
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            repository.save(p);
        }
    }

    @Override
    public void delete(Long id) {
        Product p = repository.findById(id).orElse(null);
        if (p != null) {
            repository.delete(p);
        }
    }
}
