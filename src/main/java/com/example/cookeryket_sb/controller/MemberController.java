package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j  // 로깅에 대한 추상 레이어를 제공하는 인터페이스 모음, 로깅이 필요한 부분에는 log 변수로 로그 생성하면 됨
@RestController  // 컨트롤러 선언 (@Controller와 @ResponseBody가 합쳐진 것) 주로 JSON 형태로 데이터를 클라이언트에게 반환
@RequestMapping("/user")  // 특정 URI에 대한 요청을 처리하는 메소드
@RequiredArgsConstructor  // final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어줌
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")  // URL에 변수(데이터)를 노출하지 않고 요청, 데이터를 Body에 포함, URL에 데이터가 노출되지 않음
    public void signup(@RequestBody final MemberEntity member) {
        memberService.join(member);
        System.out.println(member);
    }

    // 로그인
    @GetMapping("/login/{id}")  // URL에 변수를 포함시켜 요청, 데이터를 Header에 포함하여 전송
    public MemberEntity login(@PathVariable("id") Long memberNumber, @RequestParam("password") final String password) {
        // 아이디와 비밀번호를 받아 로그인을 처리하는 메서드
        Optional<MemberEntity> member = memberService.findByMemberId(memberNumber);
        if (member.isPresent()) {  // 해당 아이디로 회원이 존재하는 경우
            if (member.get().getMemberPw().equals(password)) {  // 비밀번호 일치
                log.info("login success!");
                return member.get();
            } else {  // 비밀번호 불일치
                log.info("wrong password!");
                return null;
            }
        } else {  // 해당 아이디로 회원이 존재하지 않는 경우
            log.info("wrong id!");
            return null;
        }
    }

    // 특정 아이디의 회원 조회
    @GetMapping("/{id}")
    public MemberEntity findMember(@PathVariable("id") Long memberNumber) {
        Optional<MemberEntity> optionalMember = memberService.findByMemberId(memberNumber);
        if (optionalMember.isPresent()) {
            return optionalMember.get();
        } else {  // 조회된 회원이 없을 경우 예외 처리
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
    }


    // 회원 정보 수정
    @PutMapping("/update/{id}")
    public MemberEntity updateMember(@PathVariable("id") Long memberNumber, @RequestBody MemberEntity memberDetails) {
        Optional<MemberEntity> optionalMember = memberService.findByMemberId(memberNumber);
        if (optionalMember.isPresent()) {
            MemberEntity member = optionalMember.get();
            member.setMemberId(memberDetails.getMemberId());
            member.setMemberPw(memberDetails.getMemberPw());
            memberService.save(member);
            return member;
        } else {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
    }

    // 회원 정보 삭제
    @DeleteMapping("/delete/{id}")
    public String deleteMember(@PathVariable("id") Long memberNumber) {
        Optional<MemberEntity> optionalMember = memberService.findByMemberId(memberNumber);
        if (optionalMember.isPresent()) {
            memberService.delete(optionalMember.get());
            return "회원 정보가 삭제되었습니다.";
        } else {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
    }
}




