package com.example.cookeryket_sb.dto;

import com.example.cookeryket_sb.entity.IngredientEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class MenuInfo {

    private String menuName;
    private List<IngredientEntity> ingredients;
    private int totalPrice;
    private String recipe;

    public MenuInfo(String menuName, List<IngredientEntity> ingredients, int totalPrice, String recipe) {

    }
}
