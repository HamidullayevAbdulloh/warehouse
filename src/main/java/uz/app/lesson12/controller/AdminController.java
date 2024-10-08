package uz.app.lesson12.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.app.lesson12.entity.*;
import uz.app.lesson12.repository.ProductRepository;
import uz.app.lesson12.service.HistoryService;
import uz.app.lesson12.service.ProductService; // Assuming you have a ProductService
import uz.app.lesson12.service.UserService;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Admin") // Use "/product" for a more specific endpoint
@RequiredArgsConstructor// Use constructor injection for better testability
public class AdminController {
    private final ProductService productService; // Autowired ProductService
    private final HistoryService historyService;
    private final UserService userService;


    @PostMapping("/getUserHistoryByID")
    public ResponseEntity<List<History>> getAllProductsBYUserID(@RequestBody long id) {
        List<History> all = historyService.getAll(id);
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @PostMapping("/deleteByID")
    public ResponseEntity<String> deleteProductByID(@RequestBody Map<String, Long> request) {
        Long userID = request.get("userID");
        Long productID = request.get("productID");
        String result = productService.deletebyid(userID, productID);
        System.out.println(result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @PostMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> all = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @PostMapping("/getByUserid")
    public ResponseEntity<User> getuserbyid(@RequestBody Long userID) {
        User byID = userService.getByID(userID);
        return ResponseEntity.status(HttpStatus.OK).body(byID);
    }

    @PutMapping("/changeRole")
    public ResponseEntity<String> changeUserRole(@RequestParam Long userId, @RequestParam String newRole) {
        String response = userService.changeRole(userId, newRole);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}