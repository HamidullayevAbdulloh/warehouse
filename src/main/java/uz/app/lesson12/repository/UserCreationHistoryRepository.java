package uz.app.lesson12.repository;

import org.springframework.data.repository.CrudRepository;
import uz.app.lesson12.entity.UserCreationHistory;

public interface UserCreationHistoryRepository extends CrudRepository<UserCreationHistory, Long> {
}
