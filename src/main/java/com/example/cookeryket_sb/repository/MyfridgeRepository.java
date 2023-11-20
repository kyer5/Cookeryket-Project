package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.MyfridgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyfridgeRepository extends JpaRepository<MyfridgeEntity, Long> {
    List<MyfridgeEntity> findByMemberEntity(MemberEntity memberEntity);


    Optional<Object> findByMemberEntityAndIngredientEntity(MemberEntity memberEntity, IngredientEntity ingredientEntity);
}
