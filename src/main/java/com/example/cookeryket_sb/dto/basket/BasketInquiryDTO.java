package com.example.cookeryket_sb.dto.basket;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BasketInquiryDTO {
    private Long ingredientKey;
    private int ingredientPrice;
    private String ingredientName;
    private int basketQuantity;
    private Long basketKey;
    private String ingredientExplain;
    private String ingredientImage;
    private int totalPrice;

    @Builder
    public BasketInquiryDTO(Long ingredientKey, int ingredientPrice, String ingredientName, int basketQuantity, Long basketKey
            , String ingredientExplain, String ingredientImage, int totalPrice) {
        this.ingredientPrice = ingredientPrice;
        this.ingredientName = ingredientName;
        this.basketQuantity = basketQuantity;
        this.basketKey = basketKey;
        this.ingredientExplain = ingredientExplain;
        this.ingredientImage = ingredientImage;
        this.totalPrice = totalPrice;
    }
}