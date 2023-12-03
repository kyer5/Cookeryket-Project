package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_detail")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailKey;

    @ManyToOne
    @JoinColumn(name = "order_key")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "ingredient_key")
    private IngredientEntity ingredientEntity;

    private int orderDetailQuantity;
}