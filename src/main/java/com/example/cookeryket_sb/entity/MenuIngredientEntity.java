package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_ingredient")
public class MenuIngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuIngredientKey;

    @ManyToOne
    @JoinColumn(name = "menu_key")
    private MenuEntity menuEntity;

    @ManyToOne
    @JoinColumn(name = "ingredient_key")
    private IngredientEntity ingredientEntity;
}