package uz.app.lesson12.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@Entity
@Table(name = "UserCreationHistory")
public class UserCreationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;  // Enum for roles (e.g., ADMIN, CLIENT)
}
