package com.example.cookeryket_sb.dto.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MemberDeleteDTO {
    private Long memberKey;
    private String memberPw;

    @Builder
    public MemberDeleteDTO(Long memberKey, String memberPw){
        this.memberKey =memberKey;
        this.memberPw=memberPw;
    }
}