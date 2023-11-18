package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ingredientNumber;

    @Column(unique = true)
    private String ingredientName;

    private int ingredientPrice;
    private int ingredientCnt;
    private String ingredientExplain;

}
