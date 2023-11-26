package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByMemberEntity(MemberEntity memberEntity);

}
