package ma.jee.spring.tp3.controller;

import ma.jee.spring.tp3.entity.Category;
import ma.jee.spring.tp3.repository.CategoryRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @QueryMapping
    public List<Category> categories() {
        return categoryRepository.findAll();
    }

    @QueryMapping
    public Category category(@Argument Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Category addCategory(@Argument CategoryInput input) {
        Category category = new Category(input.name());
        return categoryRepository.save(category);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    public static record CategoryInput(String name) {
    }
}
