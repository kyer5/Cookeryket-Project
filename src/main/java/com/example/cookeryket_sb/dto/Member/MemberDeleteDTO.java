package com.example.cookeryket_sb.dto.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MemberDeleteDTO {
    private Long memberNumber;
    private String memberPw;

    @Builder
    public MemberDeleteDTO(Long memberNumber, String memberPw){
        this.memberNumber=memberNumber;
        this.memberPw=memberPw;
    }
}