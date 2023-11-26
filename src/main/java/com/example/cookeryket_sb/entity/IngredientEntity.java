package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredient")
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientKey;

    @Column(unique = true)
    private String ingredientName;

    private int ingredientPrice;
    private int ingredientQuantity;
    private String ingredientExplain;

    @JoinTable(name = "menu_ingredient",
            joinColumns = @JoinColumn(name = "ingredient_key"),
            inverseJoinColumns = @JoinColumn(name = "menu_key"))
    @ManyToMany
    private List<MenuEntity> menuEntityList = new ArrayList<>();
}
