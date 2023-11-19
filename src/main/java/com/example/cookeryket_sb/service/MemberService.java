package com.example.cookeryket_sb.service;

import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입 처리 메서드
    public MemberEntity join(final MemberEntity member){
         System.out.println("member = " + member);
        // 회원 가입 전 중복 여부를 확인하고, 중복되지 않으면 저장
        MemberEntity joinMember = validateDuplicatedUser(member);
        return memberRepository.save(joinMember);
//        return new MemberEntity();
    }

    // 중복 회원 확인 및 중복되지 않은 경우 새로운 MemberEntity 객체 반환
    @Transactional
    public MemberEntity validateDuplicatedUser(final MemberEntity member){
        log.info("member in validate = {}", member);
        Optional<MemberEntity> optionalUser = memberRepository.findByMemberId(member.getMemberId());
        if(optionalUser.isPresent()){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }else{
            return MemberEntity.builder()
                    .memberId(member.getMemberId())
                    .memberPw(member.getMemberPw())
                    .memberName(member.getMemberName())
                    .memberPhone(member.getMemberPhone())
                    .memberEmail(member.getMemberEmail())
                    .memberAddress(member.getMemberAddress())
                    .build();
        }
    }

    // 모든 회원 리스트 조회
    public List<MemberEntity> memberList(){
        return memberRepository.findAll();
    }

    // 아이디로 회원 정보 조회 (프라이머리 키로 조회)
    public Optional<MemberEntity> findByMemberId(final Long memberNumber){
        return memberRepository.findById(memberNumber);
    }

    // 회원 정보 저장
    public MemberEntity save(MemberEntity member){
        return memberRepository.save(member);
    }

    // 회원 정보 삭제
    public void delete(MemberEntity member){
        memberRepository.delete(member);
    }
}
