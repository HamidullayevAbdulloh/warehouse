package uz.app.lesson12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.app.lesson12.entity.*;
import uz.app.lesson12.entity.UserCreationHistory ;
import uz.app.lesson12.entity.Enum;
import uz.app.lesson12.repository.*;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserCreationHistoryRepository userCreationHistoryRepository;
    private final UserReposritory userReposritory;



    public List<User> findAll() {
        List<User> all = (List<User>) userReposritory.findAll();
        return all;
    }
    public User getByID(long id) {
        Optional<User> byId = userReposritory.findById(id);
        return byId.orElse(null);
    }
    public String create(User user) {
        userReposritory.save(user);
        UserCreationHistory userCreationHistory = new UserCreationHistory();
        userCreationHistory.setName(user.getName());
        userCreationHistory.setPhone(user.getPhone());
        userCreationHistory.setRole(user.getRole());
        userCreationHistoryRepository.save(userCreationHistory) ;
        return "User created";
    }
    public String changeRole(Long userId, String newRole) {
        Optional<User> optionalUser = userReposritory.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(Role.valueOf(newRole));
            userReposritory.save(user);
            return "User role successfully changed";
        } else {
            return "User not found";
        }
    }





}