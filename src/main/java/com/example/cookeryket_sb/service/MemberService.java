package com.example.cookeryket_sb.service;

import com.example.cookeryket_sb.dto.member.*;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.repository.MemberRepository;
import com.example.cookeryket_sb.exception.CookeryketException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입 처리 메서드
    @Transactional
    public Optional<MemberEntity> save(MemberSignupDTO memberSignupDTO) {
        validateDuplicatedUser(memberSignupDTO.getMemberId());
        validateDuplicateEmail(memberSignupDTO.getMemberEmail());

        MemberEntity memberEntity = MemberEntity.builder()
                .memberId(memberSignupDTO.getMemberId())
                .memberPw(memberSignupDTO.getMemberPw())
                .memberName(memberSignupDTO.getMemberName())
                .memberPhone(memberSignupDTO.getMemberPhone())
                .memberEmail(memberSignupDTO.getMemberEmail())
                .memberAddress(memberSignupDTO.getMemberAddress())
                .build();
        log.info("member in service = {}", memberEntity);
        memberRepository.save(memberEntity);
        return memberRepository.findByMemberId(memberEntity.getMemberId());
    }

    private void validateDuplicateEmail(String memberEmail) {
        Optional<MemberEntity> member = memberRepository.findByMemberEmail(memberEmail);
        if (member.isPresent()) {
            throw new CookeryketException("중복된 이메일입니다.");
        }
    }

    // 아이디로 중복 회원 확인
    @Transactional
    public void validateDuplicatedUser(String memberId) {
        log.info("member in validate = {}", memberId);
        Optional<MemberEntity> optionalUser = memberRepository.findByMemberId(memberId);
        if (optionalUser.isPresent()) {
            throw new CookeryketException("이미 존재하는 아이디입니다.");
        }
    }

    // 로그인
    @Transactional
    public MemberEntity memberLogin(MemberLoginDTO memberLoginDTO) {
        Optional<MemberEntity> optionalMember = memberRepository.findByMemberId(memberLoginDTO.getMemberId());
        // 아이디와 비밀번호를 받아 로그인을 처리하는 메서드
        if (optionalMember.isPresent()) {  // 해당 아이디로 회원이 존재하는 경우
            MemberEntity memberEntity = optionalMember.get();
            if (memberEntity.getMemberPw().equals(memberLoginDTO.getMemberPw())) {  // 비밀번호 일치
                log.info("login success!");
                return memberEntity;
            } else {  // 비밀번호 불일치
                log.info("wrong password!");
                return null;
            }
        } else {  // 해당 아이디로 회원이 존재하지 않는 경우
            log.info("wrong id!");
            return null;
        }
    }

    // 회원 정보 수정
    @Transactional
    public void memberUpdate(final MemberUpdateDTO memberUpdateDTO) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(memberUpdateDTO.getMemberKey());
        if (memberEntity.isPresent()) {
            MemberEntity updateMemberEntity = memberEntity.get();
            updateMemberEntity.setMemberPw(memberUpdateDTO.getMemberPw());
            updateMemberEntity.setMemberName(memberUpdateDTO.getMemberName());
            updateMemberEntity.setMemberPhone(memberUpdateDTO.getMemberPhone());
            updateMemberEntity.setMemberEmail(memberUpdateDTO.getMemberEmail());
            updateMemberEntity.setMemberAddress(memberUpdateDTO.getMemberAddress());
            memberRepository.save(updateMemberEntity);
        }
    }

    // 회원 탈퇴
    @Transactional
    public void memberDelete(final MemberDeleteDTO memberDeleteDTO) {
        Optional<MemberEntity> optionalMember = memberRepository.findById(memberDeleteDTO.getMemberKey());
        MemberEntity memberEntity;
        if (optionalMember.isPresent()) {
            memberEntity = optionalMember.get();
            memberRepository.delete(memberEntity);
        }
    }

    // 회원 조회
    @Transactional
    public MemberInquiryDTO inquiryMember(Long memberKey) {
        MemberEntity memberEntity = memberRepository.findById(memberKey)
                .orElseThrow(IllegalArgumentException::new);

        MemberInquiryDTO memberInquiryDTO = new MemberInquiryDTO();
        memberInquiryDTO.setMemberId(memberEntity.getMemberId());
        memberInquiryDTO.setMemberPw(memberEntity.getMemberPw());
        memberInquiryDTO.setMemberName(memberEntity.getMemberName());
        memberInquiryDTO.setMemberPhone(memberEntity.getMemberPhone());
        memberInquiryDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberInquiryDTO.setMemberAddress(memberEntity.getMemberAddress());

        return memberInquiryDTO;
    }
}