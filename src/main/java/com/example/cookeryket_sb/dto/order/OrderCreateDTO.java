package com.example.cookeryket_sb.dto.order;

import lombok.Data;

@Data
public class OrderCreateDTO {

    private Long ingredientKey;
    private int orderQuantity;
}
