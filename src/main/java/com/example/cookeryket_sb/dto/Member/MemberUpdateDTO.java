package com.example.cookeryket_sb.dto.Member;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDTO {
    private Long memberKey;
    private String memberPw;
    private String memberName;
    private String memberPhone;
    private String memberEmail;
    private String memberAddress;
}
