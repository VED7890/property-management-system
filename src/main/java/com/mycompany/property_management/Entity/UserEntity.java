package com.mycompany.property_management.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "USER_TABLE",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "OWNER_EMAIL")
        }
)
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ownerName;

    @Column(name = "OWNER_EMAIL", nullable = false, unique = true)
    private String ownerEmail;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;
}
