package com.example.cookeryket_sb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MyfridgeListDTO {  // 사용자한테 보여줄 마이냉장고 정보 리스트 -> 재료 이름만 필요하니까

    private Long myfridgeNumber;
    private String ingredientName;

    public MyfridgeListDTO(Long myfridgeNumber, String ingredientName) {
        this.myfridgeNumber = myfridgeNumber;
        this.ingredientName = ingredientName;
    }
}
