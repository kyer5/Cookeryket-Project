package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailNumber;

    @ManyToOne
    private OrderEntity orderNumber;

    @ManyToOne
    private IngredientEntity ingredientNumber;

    private int orderDetailCnt;
}
