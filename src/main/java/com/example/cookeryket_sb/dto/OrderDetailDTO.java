package com.example.cookeryket_sb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDTO {
    private String ingredientName;
    private int ingredientPrice;
    private int orderDetailCount;
}
