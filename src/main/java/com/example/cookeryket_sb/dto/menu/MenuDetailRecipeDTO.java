package com.example.cookeryket_sb.dto.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MenuDetailRecipeDTO {

    private String menuName;
    private int totalPrice;
    private String menuRecipe;

}
