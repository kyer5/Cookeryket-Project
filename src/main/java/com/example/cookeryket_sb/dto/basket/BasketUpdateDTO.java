package com.example.cookeryket_sb.dto.basket;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class BasketUpdateDTO {

    private Long memberKey;
    private int basketQuantity;

    @Builder
    public BasketUpdateDTO(Long memberKey, int basketQuantity) {
        this.memberKey = memberKey;
        this.basketQuantity = basketQuantity;
    }
}