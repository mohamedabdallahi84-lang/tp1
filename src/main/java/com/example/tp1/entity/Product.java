package com.example.tp1.entity;


import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.* ;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor     // constructeur vide (requis par JPA)
@AllArgsConstructor// constructeur avec tous les champs
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Le nom est obligatoire")
    private String name;
    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    private Double price;
    private String description;


}
