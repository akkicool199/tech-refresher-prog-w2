package repo;

import entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import service.ProductSummary;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<ProductSummary> findByPriceLessThan(double price);
}
