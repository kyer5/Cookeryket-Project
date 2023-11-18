package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.MyfridgeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyfridgeRepository extends JpaRepository<MyfridgeEntity, Long> {
    List<MyfridgeEntity> findByMemberNumber(Long memberNumber);
//    List<MyfridgeEntity> findByIngredientName(Long ingredientId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MyfridgeEntity m WHERE m.memberNumber.memberNumber = :memberId AND m.ingredientNumber.ingredientNumber = :ingredientNumber")
    void deleteMyfridge(Long memberNumber, int ingredientNumber);
//    List<MyfridgeEntity> deleteMyfridge(IngredientEntity ingredient);


}
