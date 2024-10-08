package uz.app.lesson12.repository;

import org.springframework.data.repository.CrudRepository;
import uz.app.lesson12.entity.History;

import java.util.List;

public interface HistoryRepository extends CrudRepository<History, Integer> {
    List<History> findAllByUserId(Long userId);
}
