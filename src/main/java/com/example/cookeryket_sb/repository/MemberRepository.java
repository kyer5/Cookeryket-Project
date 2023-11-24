package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository  // 해당 클래스가 데이터베이스와 관련된 리포지토리임을 나타냄
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 아이디로 회원 정보 조회
    Optional<MemberEntity> findByMemberNumber(final Long memberNumber);

}
