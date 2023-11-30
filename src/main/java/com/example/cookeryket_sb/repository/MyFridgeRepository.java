package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.MyFridgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyFridgeRepository extends JpaRepository<MyFridgeEntity, Long> {
    // 주어진 MemberEntity를 가진 모든 MyFridgeEntity를 찾아서 그 결과를 List 형태로 반환
    // MyFridgeEntity의 MemberEntity 필드와 매개 변수로 전달된 MemberEntity가 일치하는 모든 MyFridgeEntity를 찾는다.
    List<MyFridgeEntity> findByMemberEntity(MemberEntity memberEntity);

    boolean existsByMemberEntityAndIngredientEntity(MemberEntity memberEntity, IngredientEntity ingredientEntity);

}
