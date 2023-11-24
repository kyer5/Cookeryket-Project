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
@Table(name="menu")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuNumber;

    private String menuName;
    private String menuRecipe;

    @JoinTable(name = "menu_ingredient",
            joinColumns = @JoinColumn(name = "menu_number"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_number"))
    @ManyToMany
    private List<IngredientEntity> ingredientEntityList = new ArrayList<>();

}
