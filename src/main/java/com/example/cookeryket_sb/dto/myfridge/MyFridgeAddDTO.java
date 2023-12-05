package com.example.cookeryket_sb.dto.myfridge;

import lombok.*;

@Setter
@Builder
@ToString
@NoArgsConstructor
@Getter
public class MyFridgeAddDTO {
    private String ingredientName;

    @Builder
    public MyFridgeAddDTO(String ingredientName){
        this.ingredientName=ingredientName;
    }
}