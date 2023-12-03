package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
}
