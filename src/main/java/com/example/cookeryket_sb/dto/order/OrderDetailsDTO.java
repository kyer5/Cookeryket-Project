package com.example.cookeryket_sb.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDTO {
    private Long orderKey;
    private String[] ingredientName;
    private int[] ingredientPrice;
    private int[] orderDetailQuantity;
    private int totalPrice;
    private String orderDate;

    private String memberName;
    private String memberPhone;
    private String memberAddress;
}