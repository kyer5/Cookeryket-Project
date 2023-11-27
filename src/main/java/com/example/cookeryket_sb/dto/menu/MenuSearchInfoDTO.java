package com.example.cookeryket_sb.dto.menu;

import com.example.cookeryket_sb.dto.IngredientDTO;
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

}
