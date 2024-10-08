package uz.app.lesson12.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.app.lesson12.entity.History;
import uz.app.lesson12.repository.ProductRepository;
import uz.app.lesson12.service.HistoryService;
import uz.app.lesson12.service.ProductService; // Assuming you have a ProductService


import java.util.List;


@RestController
@RequestMapping("/history") // Use "/product" for a more specific endpoint
@RequiredArgsConstructor// Use constructor injection for better testability
public class HistoryController {
    private final ProductService productService; // Autowired ProductService
    private final HistoryService historyService;
    private final ProductRepository repository;


    @PostMapping("/getUserHistorybyID")
    public ResponseEntity<List<History>> getAllProducts(@RequestBody long id) {
        List<History> allByUserId = historyService.getUserHistorybyID(id);
        return ResponseEntity.status(HttpStatus.OK).body(allByUserId);
    }







}