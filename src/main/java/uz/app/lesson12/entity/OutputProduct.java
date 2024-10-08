package uz.app.lesson12.entity;

import lombok.Data;

import java.util.UUID;
@Data
public class OutputProduct {
    private UUID id = UUID.randomUUID();
    private long productId;
    private String name;
    private String description;
    private String brand;
    private Integer kg;
    private long categoryId;
    private long warehouseId;
    private Long userId;

}
