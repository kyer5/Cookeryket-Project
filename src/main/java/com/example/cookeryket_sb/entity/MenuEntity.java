package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="menu")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuKey;

    private String menuName;
    private String menuRecipe;
    private String menuImage;

    @JoinTable(name = "menu_ingredient",
            joinColumns = @JoinColumn(name = "menu_key"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_key"))
    @ManyToMany
    private List<IngredientEntity> ingredientEntityList = new ArrayList<>();

}
