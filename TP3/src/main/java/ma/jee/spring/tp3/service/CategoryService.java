package ma.jee.spring.tp3.service;

import ma.jee.spring.tp3.entity.Category;
import ma.jee.spring.tp3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoryService implements IService<Category>{
    @Autowired
    private CategoryRepository repository;

    @Override
    public Collection<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void insert(Category Category) {
        repository.save(Category);
    }

    @Override
    public void update(Category category, Long id) {
        Category c = repository.findById(id).orElse(null);
        if (c != null){
            c.setName(category.getName());
            repository.save(c);
        }
    }

    @Override
    public void delete(Long id) {
        Category c = repository.findById(id).orElse(null);
        if (c != null) {
            repository.delete(c);
        }
    }
}
