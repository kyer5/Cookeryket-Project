package com.example.cookeryket_sb.dto.order;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderHistoryDTO {
    private Long orderKey;
    private String orderDate;
    private String ingredientName;
    private int totalPrice;

    @Builder
    public OrderHistoryDTO(Long orderKey, String orderDate, String ingredientName
            , int totalPrice) {
        this.orderKey = orderKey;
        this.orderDate= orderDate;
        this.ingredientName = ingredientName;
        this.totalPrice = totalPrice;

    }
}
