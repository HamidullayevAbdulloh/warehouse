package uz.app.lesson12.repository;

import org.springframework.data.repository.CrudRepository;
import uz.app.lesson12.entity.User;

public interface UserReposritory extends CrudRepository<User, Long> {
}
