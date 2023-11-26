package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.BasketEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<BasketEntity,Long>{
    List<BasketEntity> findByMemberEntity(MemberEntity memberEntity);
}
