package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderdetail")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailNumber;

    @ManyToOne
    @JoinColumn(name = "order_number")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "ingredient_number")
    private IngredientEntity ingredient;

    private int orderDetailQuantity;
}