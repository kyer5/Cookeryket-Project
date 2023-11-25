package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.MyfridgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyfridgeRepository extends JpaRepository<MyfridgeEntity, Long> {
    // 주어진 MemberEntity를 가진 모든 MyfridgeEntity를 찾아서 그 결과를 List 형태로 반환
    // MyfridgeEntity의 MemberEntity 필드와 매개 변수로 전달된 MemberEntity가 일치하는 모든 MyfridgeEntity를 찾는다.
    List<MyfridgeEntity> findByMemberEntity(MemberEntity memberEntity);

}
