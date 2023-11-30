package com.example.cookeryket_sb.dto.myfridge;

import lombok.Getter;

@Getter
public class MyFridgeListDTO {  // 사용자한테 보여줄 마이냉장고 정보 리스트 -> 재료 이름만 필요하니까

    private Long myfridgeKey;
    private String ingredientName;

    public MyFridgeListDTO(Long myfridgeKey, String ingredientName) {
        this.myfridgeKey = myfridgeKey;
        this.ingredientName = ingredientName;
    }
}
