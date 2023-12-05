package com.example.cookeryket_sb.dto.menu;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MenuEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuSearchInfoDTO {
    private String menuName;
    private int totalPrice;
    private String menuImage;

    public static MenuSearchInfoDTO of(MenuEntity menuEntity) {
        return null;
    }
}

