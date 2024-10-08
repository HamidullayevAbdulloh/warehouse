package uz.app.lesson12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.lesson12.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
