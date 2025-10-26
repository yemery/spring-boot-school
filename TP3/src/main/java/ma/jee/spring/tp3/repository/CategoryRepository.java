package ma.jee.spring.tp3.repository;

import ma.jee.spring.tp3.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
