package com.example.cookeryket_sb.dto.basket;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class BasketAddDTO {
    private Long ingredientKey;
    private int basketQuantity;

    @Builder
    public BasketAddDTO(Long ingredientKey, int basketQuantity) {
        this.ingredientKey = ingredientKey;
        this.basketQuantity = basketQuantity;
    }
}
