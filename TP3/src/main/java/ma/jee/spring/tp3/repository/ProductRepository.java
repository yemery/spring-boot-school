package ma.jee.spring.tp3.repository;

import ma.jee.spring.tp3.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
