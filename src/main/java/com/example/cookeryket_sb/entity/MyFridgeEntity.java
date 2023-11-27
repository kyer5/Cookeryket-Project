package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity  // 해당 클래스를 데이터베이스 테이블과 매핑하는 엔티티 클래스로 선언
@Getter
@Setter
@ToString  // 해당 클래스의 toString 메소드를 자동으로 생성
@AllArgsConstructor  // 모든 필드를 인자로 받는 생성자를 생성
@NoArgsConstructor  // 인자가 없는 기본 생성자 생성
@Table(name="myfridge")  // 해당 엔티티 클래스를 'myfridge' 테이블과 매핑
public class MyFridgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myFridgeKey;

    @ManyToOne  // 다대일 관계. 여러 My냉장고 엔티티가 하나의 멤버 엔티티를 참조할 수 있음
    @JoinColumn(name="member_key")  // 외래키 필드를 나타내며, 외래키는 'member_key' 컬럼에 매핑됨
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name= "ingredient_key")
    private IngredientEntity ingredientEntity;
}



