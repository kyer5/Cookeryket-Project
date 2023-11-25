package com.example.cookeryket_sb.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotalCostDTO {
    private String menuName;
    private int totalPrice;
}
