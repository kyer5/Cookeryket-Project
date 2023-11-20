package com.example.cookeryket_sb.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {
    private Long ingredientNumber;

    private String ingredientName;

    private int ingredientPrice;
    private int ingredientCnt;
    private String ingredientExplain;

}
