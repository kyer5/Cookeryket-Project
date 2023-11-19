package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.MyfridgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyfridgeRepository extends JpaRepository<MyfridgeEntity, Long> {
    List<MyfridgeEntity> findByMemberEntity(MemberEntity memberEntity);

    MyfridgeEntity findByMemberEntityAndIngredientEntity(MemberEntity memberEntity, IngredientEntity ingredientEntity);


}
