package uz.app.lesson12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.lesson12.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {


}
