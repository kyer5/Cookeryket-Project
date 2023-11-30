package com.example.cookeryket_sb.dto.myfridge;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyFridgeDTO {  // 마이 냉장고의 모든 정보

    private Long myFridgeKey;
    private Long memberKey;
    private Long ingredientKey;

    public MyFridgeDTO(Long memberKey, Long ingredientKey) {
        this.memberKey = memberKey;
        this.ingredientKey = ingredientKey;
    }
}
