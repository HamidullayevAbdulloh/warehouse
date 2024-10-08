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
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final CategoryRepository categoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final UserReposritory userReposritory;

//    public void saveHistory(Enum anenum,  Long product_Id , Long category_Id , Long wareHauseId , Long userID , String description ) {
//        Optional<Product> productID = productRepository.findById(product_Id);
//        Product product = productID.get();
//        Optional<Category> categryID = categoryRepository.findById(category_Id);
//        Category category = categryID.get();
//        Optional<Warehouse> branch = branchRepository.findById(wareHauseId);
//        Warehouse warehouse = branch.get();
//        Optional<User> byId = userReposritory.findById(userID);
//        User user = byId.get();
//
//        History history = new History();
//        history.setCategory(category);
//        history.setProduct(product);
//        history.setWarehouse(warehouse);
//        history.setUser(user);
//        history.setDescription(description);
//        history.setTransferEnum(anenum);
//        historyRepository.save(history);
//    }

public void saveHistory(
        Enum anenum,
        Long productId,
        Long categoryId,
        Long warehouseId,
        Long userId,
        String description
) {
    Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

    Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + categoryId));

    Warehouse warehouse = branchRepository.findById(warehouseId)
            .orElseThrow(() -> new IllegalArgumentException("Warehouse not found with id: " + warehouseId));

    User user = userReposritory.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

    History history = new History();
    history.setCategory(category);
    history.setProduct(product);
    history.setWarehouse(warehouse);
    history.setUser(user);
    history.setDescription(description);
    history.setTransferEnum(anenum);

    historyRepository.save(history);
}


public List<History> getUserHistorybyID(Long userId) {
    List<History> allByUserId = historyRepository.findAllByUserId(userId);
    return allByUserId;
}

public List<History> getAll(Long userId) {
    Optional<User> byId = userReposritory.findById(userId);
    User user = byId.get();
    if (user.getRole() == Role.ADMIN) {
        List<History> all = (List<History>) historyRepository.findAll();
        return all;
    }
    return null;
}
}
