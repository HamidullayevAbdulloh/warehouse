package uz.app.lesson12.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "Warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String mainPhone;

    @OneToMany(mappedBy = "warehouse")
    private List<Product> products;  // List of products in the warehouse

    // Getters and Setters
}
