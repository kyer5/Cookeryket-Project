package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity  // JPA 엔티티임을 나타냄
@Getter  // Getter 메서드를 자동으로 생성함 (Lombok 어노테이션)
@Setter
@ToString  // toString 메서드를 자동으로 생성함 (Lombok 어노테이션)
@NoArgsConstructor  // 인자가 없는 기본 생성자를 생성함 (Lombok 어노테이션)
@Table(name = "member")  // member 테이블과 매핑됨을 나타냄
public class MemberEntity {

    @Id  // 기본 키를 나타냄
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

    @Builder  // 빌더 패턴을 사용하여 객체 생성을 해주는 빌더 메서드를 생성함 (Lombok 어노테이션)
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