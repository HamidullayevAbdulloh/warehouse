package uz.app.lesson12.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.app.lesson12.entity.History;
import uz.app.lesson12.entity.InputProduct;
import uz.app.lesson12.entity.OutputProduct;
import uz.app.lesson12.entity.Product;
import uz.app.lesson12.repository.ProductRepository;
import uz.app.lesson12.service.HistoryService;
import uz.app.lesson12.service.ProductService; // Assuming you have a ProductService


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product") // Use "/product" for a more specific endpoint
@RequiredArgsConstructor// Use constructor injection for better testability
public class ProductController {
    private final ProductService productService; // Autowired ProductService
    private final HistoryService historyService;
    private final ProductRepository repository;



@PostMapping("/inputsave")
public ResponseEntity<String> saveProduct(@RequestBody InputProduct inputProduct) {
    System.out.println(inputProduct);
    productService.SaveByInPut(inputProduct);
    return ResponseEntity.status(HttpStatus.CREATED).body("Product saved successfully!");
}
    @PostMapping("/outputsave")
    public ResponseEntity<String> saveProductbyoutput(@RequestBody OutputProduct outputProduct) {
        System.out.println(outputProduct);
        productService.OutPutBProduct(outputProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product saved successfully!");
    }
    @PostMapping("/getProductbyId")
    public ResponseEntity<Product> getbyid(@RequestBody long id) {
        System.out.println(id);
        Product getbyid = productService.getbyid(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(getbyid);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }







}
