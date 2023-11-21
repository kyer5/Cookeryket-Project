package com.example.cookeryket_sb.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyfridgeDTO {

    private Long myfridgeNumber;
    private Long memberNumber;
    private Long ingredientNumber;

    public MyfridgeDTO(Long memberNumber, Long ingredientNumber) {
        this.memberNumber = memberNumber;
        this.ingredientNumber = ingredientNumber;
    }
}
