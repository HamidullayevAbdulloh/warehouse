package uz.app.lesson12.entity;

import lombok.Data;

@Data
public class InputProduct {
    private long id;
    private String name;
    private String description;
    private String brand;
    private Integer kg;
    private long categoryId;
    private long warehouseId;
    private Long userId;

}
