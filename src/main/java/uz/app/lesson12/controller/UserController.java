package uz.app.lesson12.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.app.lesson12.entity.History;
import uz.app.lesson12.entity.Product;
import uz.app.lesson12.entity.User;
import uz.app.lesson12.entity.UserCreationHistory;
import uz.app.lesson12.repository.HistoryRepository;
import uz.app.lesson12.repository.UserReposritory;
import uz.app.lesson12.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService; // Injecting UserService
    private final UserReposritory userReposritory; // Injecting UserService
    private final HistoryRepository historyRepository; // Injecting UserService

    @PostMapping("/add") // Change to POST for adding a user
    public ResponseEntity<String> addUser(@RequestBody User user) {
        String response = userService.create(user); // Assuming create() method handles user creation
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // Return 201 Created
    }
    @PostMapping("/showOwnHistoryByID") // Change to POST for adding a user
    public ResponseEntity<List<History>> addUser(@RequestBody Long id) {
        List<History> allByUserId = historyRepository.findAllByUserId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(allByUserId); // Return 201 Created
    }




}
