package uz.app.lesson12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.app.lesson12.entity.*;
import uz.app.lesson12.entity.Enum;
import uz.app.lesson12.repository.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
private final ProductRepository productRepository;
private final CategoryRepository categoryRepository;
private final BranchRepository branchRepository;
private final UserReposritory userReposritory;
private final HistoryRepository historyRepository;

    @Autowired
    HistoryService historyService;

public List<Product> findAll() {
    return productRepository.findAll();
}
public void SaveByInPut(InputProduct inputProduct) {
    Product product = new Product();
    product.setName(inputProduct.getName());
    product.setDescription(inputProduct.getDescription());
    product.setKg(inputProduct.getKg());
    product.setBrand(inputProduct.getBrand());

    Optional<Category> byId = categoryRepository.findById(inputProduct.getCategoryId());
    if (byId.isPresent()) {
        product.setCategory(byId.get());
    } else {
        Category category = new Category();
        category.setName(String.valueOf(inputProduct.getCategoryId()));
        categoryRepository.save(category);  // Save the category
        product.setCategory(category);
    }

    Optional<Warehouse> byId1 = branchRepository.findById(inputProduct.getWarehouseId());
    if (byId1.isPresent()) {
        product.setWarehouse(byId1.get());
    } else {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("1");
        warehouse.setAddress("1");
        warehouse.setMainPhone("1");
        branchRepository.save(warehouse);  // Save the warehouse
        product.setWarehouse(warehouse);
    }

    if (inputProduct.getKg() > 0) {
        productRepository.save(product);  // Save the product first

        // Now save the history with the product ID
        historyService.saveHistory(
                Enum.INPUT,
                product.getId(),
                product.getCategory().getId(),
                product.getWarehouse().getId(),
                inputProduct.getUserId(),
                "Success"
        );
    }
}


    public void OutPutBProduct(OutputProduct outputProduct) {
        // Fetch the product by its ID
        Optional<Product> productOptional = productRepository.findById(outputProduct.getProductId());
        Optional<User> userOptional = userReposritory.findById(outputProduct.getUserId());

        if (userOptional.isEmpty()) {
            System.out.println("User not found.");
            return; // or throw an exception
        }

        if (productOptional.isEmpty()) {
            System.out.println("Product not found.");
            return; // or throw an exception
        }

        Product product = productOptional.get();

        // Ensure the output KG is valid
        if (outputProduct.getKg() <= 0 || outputProduct.getKg() > product.getKg()) {
            System.out.println("Invalid KG amount.");
            return; // or throw an exception
        }

        int remainingKg = product.getKg() - outputProduct.getKg();

        // Set the userId or other necessary fields
        if (remainingKg == 0) {
            // If no quantity remains, delete the product
            productRepository.deleteById(product.getId());
            historyService.saveHistory(Enum.OUTPUT, product.getId(), product.getCategory().getId(), product.getWarehouse().getId(), outputProduct.getUserId(), "Bought , depleted and deleted !");
        } else {
            historyService.saveHistory(Enum.OUTPUT, product.getId(), product.getCategory().getId(), product.getWarehouse().getId(), outputProduct.getUserId(), "successfully bought");
            product.setKg(remainingKg);
            productRepository.save(product);
        }
    }


public Product getbyid(long id) {
    Optional<Product> byId = productRepository.findById(id);
    Product product = byId.get();
    System.out.println(product);
    return product;
}
    public String deletebyid(long userID, long productID) {
        Optional<User> userOptional = userReposritory.findById(userID);
        if (userOptional.isEmpty()) {
            return "User not found";
        }

        User user = userOptional.get();
        if (!user.getRole().equals(Role.ADMIN)) {
            return "User is not authorized to delete products";
        }
        if (productRepository.existsById(productID)) {
            productRepository.deleteById(productID);

            return "Product deleted successfully";
        } else {
            return "Product not found";
        }
    }









}
