package com.example.cookeryket_sb.dto.menu;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class MenuDetailIngredientDTO {

    private String menuName;
    private int totalPrice;
    private String[] ingredientNames;
    private int[] ingredientPrices;
    private boolean[] haves;

}
