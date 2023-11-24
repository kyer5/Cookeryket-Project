package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j  // 로깅에 대한 추상 레이어를 제공하는 인터페이스 모음, 로깅이 필요한 부분에는 log 변수로 로그 생성하면 됨
@RestController  // 컨트롤러 선언 (@Controller와 @ResponseBody가 합쳐진 것) 주로 JSON 형태로 데이터를 클라이언트에게 반환
@RequestMapping("/user")  // 특정 URI에 대한 요청을 처리하는 메소드
@RequiredArgsConstructor  // final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어줌
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")  // URL에 변수(데이터)를 노출하지 않고 요청, 데이터를 Body에 포함, URL에 데이터가 노출되지 않음
    public void signup(@RequestBody final MemberEntity memberEntity) {
        memberService.join(memberEntity);
        System.out.println(memberEntity);
    }


    // 로그인
    @GetMapping("/login/{memberNumber}")  // URL에 변수를 포함시켜 요청, 데이터를 Header에 포함하여 전송
    public MemberEntity login(@PathVariable("memberNumber") Long memberNumber, @RequestParam("memberPw") final String memberPw) {
        // 아이디와 비밀번호를 받아 로그인을 처리하는 메서드
        Optional<MemberEntity> memberEntity = memberService.findByMemberId(memberNumber);
        if (memberEntity.isPresent()) {  // 해당 아이디로 회원이 존재하는 경우
            if (memberEntity.get().getMemberPw().equals(memberPw)) {  // 비밀번호 일치
                log.info("login success!");
                return memberEntity.get();
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
    @PutMapping("/update/{memberNumber}")
    public MemberEntity updateMember(@PathVariable("memberNumber") Long memberNumber, @RequestBody MemberEntity memberDetails) {
        Optional<MemberEntity> optionalMember = memberService.findByMemberId(memberNumber);
        if (optionalMember.isPresent()) {
            MemberEntity memberEntity = optionalMember.get();
            memberEntity.setMemberId(memberDetails.getMemberId());
            memberEntity.setMemberPw(memberDetails.getMemberPw());
            memberService.save(memberEntity);
            return memberEntity;
        } else {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
    }


    // 회원 정보 삭제
    @DeleteMapping("/delete/{memberNumber}")
    public String deleteMember(@PathVariable("memberNumber") Long memberNumber) {
        Optional<MemberEntity> memberEntity = memberService.findByMemberId(memberNumber);
        if (memberEntity.isPresent()) {
            memberService.delete(memberEntity.get());
            return "회원 정보가 삭제되었습니다.";
        } else {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
    }
}




