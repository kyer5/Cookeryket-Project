package com.example.cookeryket_sb.service;

import com.example.cookeryket_sb.controller.ResponseDTO;
import com.example.cookeryket_sb.dto.Member.MemberDeleteDTO;
import com.example.cookeryket_sb.dto.Member.MemberLoginDTO;
import com.example.cookeryket_sb.dto.Member.MemberSignupDTO;
import com.example.cookeryket_sb.dto.Member.MemberUpdateDTO;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.repository.MemberRepository;
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
        ResponseDTO responseDTO = validateDuplicatedUser(memberSignupDTO.getMemberId());
        if (responseDTO.getStatus() != 200) {
            throw new IllegalArgumentException(responseDTO.getMessage());
        }
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

    // 아이디로 중복 회원 확인
    @Transactional
    public ResponseDTO validateDuplicatedUser(String memberId){
        log.info("member in validate = {}", memberId);
        Optional<MemberEntity> optionalUser = memberRepository.findByMemberId(memberId);
        if(optionalUser.isPresent()){
            return ResponseDTO.builder()
                    .status(409)
                    .message("이미 존재하는 아이디 입니다.")
                    .build();
        }else{
            return ResponseDTO.builder()
                    .status(200)
                    .build();
        }
    }

    // 로그인
    @Transactional
    public MemberEntity memberLogin(MemberLoginDTO memberLoginDTO){
        Optional<MemberEntity> optionalMember = memberRepository.findByMemberId(memberLoginDTO.getMemberId());
        // 아이디와 비밀번호를 받아 로그인을 처리하는 메서드
        if(optionalMember.isPresent()){  // 해당 아이디로 회원이 존재하는 경우
            MemberEntity memberEntity = optionalMember.get();
            if(memberEntity.getMemberPw().equals(memberLoginDTO.getMemberPw())){  // 비밀번호 일치
                log.info("login success!");
                return memberEntity;
            }else{  // 비밀번호 불일치
                log.info("wrong password!");
                return null;
            }
        }else{  // 해당 아이디로 회원이 존재하지 않는 경우
            log.info("wrong id!");
            return null;
        }
    }



    // 회원 정보 수정
    @Transactional
    public MemberEntity memberUpdate(final MemberUpdateDTO memberUpdateDTO){
        Optional<MemberEntity> optionalMember = memberRepository.findById(memberUpdateDTO.getMemberNumber());
        log.info("optionalMember = {}", optionalMember);
        if(optionalMember.isPresent()){
            MemberEntity memberEntity=MemberEntity.builder()
                    .memberPw(memberUpdateDTO.getMemberPw())
                    .memberName(memberUpdateDTO.getMemberName())
                    .memberPhone(memberUpdateDTO.getMemberPhone())
                    .memberEmail(memberUpdateDTO.getMemberEmail())
                    .memberAddress(memberUpdateDTO.getMemberAddress())
                    .build();
            log.info("update member = {}", memberEntity);
            memberRepository.save(memberEntity);
        }
        return memberRepository.findById(memberUpdateDTO.getMemberNumber()).get();
    }




    // 회원 정보 삭제
    @Transactional
    public void memberDelete(final MemberDeleteDTO memberDeleteDTO){
        Optional<MemberEntity> optionalMember = memberRepository.findById(memberDeleteDTO.getMemberNumber());
        MemberEntity memberEntity;
        if(optionalMember.isPresent()){
            memberEntity=optionalMember.get();
        } else{
            throw new IllegalStateException("해당 회원이 존재하지 않습니다.");
        }
        if(memberEntity.getMemberPw().equals(memberDeleteDTO.getMemberPw())){
            log.info("회원 탈퇴 = {}", memberEntity);
            memberRepository.delete(memberEntity);
        }else{
            throw new IllegalArgumentException("비밀번호 오류! 회원 탈퇴에 실패하였습니다.");
        }
    }
}
