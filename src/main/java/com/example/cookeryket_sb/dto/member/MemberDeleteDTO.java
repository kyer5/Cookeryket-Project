package com.example.cookeryket_sb.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MemberDeleteDTO {
    private Long memberKey;

    @Builder
    public MemberDeleteDTO(String memberPw){
        this.memberKey = memberKey;
    }
}