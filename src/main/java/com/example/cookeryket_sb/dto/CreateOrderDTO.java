package com.example.cookeryket_sb.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class CreateOrderDTO {

    private Long ingredientNumber;
    private int orderQuantity;
}
