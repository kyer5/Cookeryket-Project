package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.dto.member.MemberDeleteDTO;
import com.example.cookeryket_sb.dto.member.MemberLoginDTO;
import com.example.cookeryket_sb.dto.member.MemberUpdateDTO;
import com.example.cookeryket_sb.dto.member.MemberSignupDTO;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public MemberEntity signup(@RequestBody MemberSignupDTO memberSignupDTO) {
        log.info("signupDTO = {}", memberSignupDTO);
        Optional<MemberEntity> joinMember = memberService.save(memberSignupDTO);
        log.info("Member = {}", joinMember);
        return joinMember.get();
    }

    // 로그인
    @PostMapping("/login")
    public Long signIn(@RequestBody MemberLoginDTO memberLoginDTO) {
        MemberEntity memberEntity = memberService.memberLogin(memberLoginDTO);
        if (memberEntity == null) {
            return 0L;
        }
        return memberEntity.getMemberKey();
    }

    // 회원 정보 수정
    @PutMapping("/update")
    public void updateMember(@RequestBody MemberUpdateDTO memberUpdateDTO) {
        memberService.memberUpdate(memberUpdateDTO);
    }

    // 회원 탈퇴
    @DeleteMapping("/delete")
    public void deleteMember(@RequestBody MemberDeleteDTO memberDeleteDTO) {
        memberService.memberDelete(memberDeleteDTO);
    }
}
