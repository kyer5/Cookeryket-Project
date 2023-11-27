package com.example.cookeryket_sb.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MemberLoginDTO {
    private String memberId;
    private String memberPw;

    @Builder
    public MemberLoginDTO(String memberId, String memberPw){
        this.memberId=memberId;
        this.memberPw=memberPw;
    }
}
