package com.example.cookeryket_sb.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {

    private String menuName;
    private double totalCost;
    private List<IngredientDTO> ingredients;
    private String menuRecipe;

}
