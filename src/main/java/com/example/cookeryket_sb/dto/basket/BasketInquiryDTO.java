package com.example.cookeryket_sb.dto.basket;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BasketInquiryDTO {
    private int ingredientPrice;
    private String ingredientName;
    private int basketQuantity;
    private Long basketKey;

    @Builder
    public BasketInquiryDTO(int ingredientPrice, String ingredientName, int basketQuantity, Long basketKey) {
        this.ingredientPrice = ingredientPrice;
        this.ingredientName = ingredientName;
        this.basketQuantity = basketQuantity;
        this.basketKey = basketKey;
    }
}