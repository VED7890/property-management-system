package com.mycompany.property_management.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PROPERTY_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;                 // lowercase 'id'

    private String title;
    private String description;
    private String ownerName;        // camelCase
    private String ownerEmail;       // camelCase
    private Double price;
    private String address;
}
