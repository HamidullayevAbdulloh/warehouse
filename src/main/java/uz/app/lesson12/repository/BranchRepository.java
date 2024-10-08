package uz.app.lesson12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.lesson12.entity.Warehouse;

public interface BranchRepository extends JpaRepository<Warehouse , Long> {
}
