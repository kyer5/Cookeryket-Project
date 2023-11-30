package com.example.cookeryket_sb.dto.ingredient;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientSearchDTO {

    private Long ingredientKey;
    private String ingredientName;

}
