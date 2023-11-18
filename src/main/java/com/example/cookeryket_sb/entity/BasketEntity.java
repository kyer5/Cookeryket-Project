package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BasketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int basketNumber;

    @ManyToOne
    private MenuEntity memberNumber;

    @ManyToOne
    private IngredientEntity ingredientNumber;

    private int basketCnt;

}
