package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MenuIngredientEntity {
    @Id
    @ManyToOne
    private MenuEntity menu;

    @Id
    @ManyToOne
    private MenuEntity Ingredient;
}
