package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberKey;

    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberPhone;
    private String memberEmail;
    private String memberAddress;

    @OneToMany(mappedBy = "memberEntity")
    private List<MyFridgeEntity> myFridges = new ArrayList<>();

    @Builder
    public MemberEntity(Long memberKey, String memberId, String memberPw, String memberName, String memberPhone, String memberEmail, String memberAddress) {
        this.memberKey = memberKey;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberAddress = memberAddress;
    }

}