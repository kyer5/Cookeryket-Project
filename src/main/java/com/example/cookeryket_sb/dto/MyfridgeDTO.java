package com.example.cookeryket_sb.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyfridgeDTO {  // 마이 냉장고의 모든 정보

    private Long myfridgeNumber;
    private Long memberNumber;
    private Long ingredientNumber;

    public MyfridgeDTO(Long memberNumber, Long ingredientNumber) {
        this.memberNumber = memberNumber;
        this.ingredientNumber = ingredientNumber;
    }
}
