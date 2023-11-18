package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j  // 로깅에 대한 추상 레이어를 제공하는 인터페이스 모음, 로깅이 필요한 부분에는 log 변수로 로그 생성하면 됨
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor  // final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어줌
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")  // URL에 변수(데이터)를 노출하지 않고 요청, 데이터를 Body에 포함, URL에 데이터가 노출되지 않음
    public void signup(@RequestBody final MemberEntity member){  // Member 부정확
        memberService.join(member);
        System.out.println(member);
    }

    // 로그인
    @GetMapping("/login/{id}")  // URL에 변수를 포함시켜 요청, 데이터를 Header에 포함하여 전송
    public MemberEntity login(@PathVariable("id") final String id, @RequestParam("password") final String password){
        // 아이디와 비밀번호를 받아 로그인을 처리하는 메서드
        Optional<MemberEntity> member = memberService.findByMemberId(id);
        if(member.isPresent()){  // 해당 아이디로 회원이 존재하는 경우
            if(member.get().getMemberPw().equals(password)){  // 비밀번호 일치
                log.info("login success!");
                return member.get();
            }else{  // 비밀번호 불일치
                log.info("wrong password!");
                return null;
            }
        } else{  // 해당 아이디로 회원이 존재하지 않는 경우
            log.info("wrong id!");
            return null;
        }
    }

    // 모든 회원 리스트 조회
    @GetMapping("/list")
    public List<MemberEntity> memberList(){
        return memberService.memberList();
    }

    // 특정 아이디의 회원 조회
    @GetMapping("/{id}")
    public MemberEntity findMember(@PathVariable("id") Long memberNumber){
        Optional<MemberEntity> optionalMember=memberService.findById(memberNumber);
        if(optionalMember.isPresent()){
            return optionalMember.get();
        }else{  // 조회된 회원이 없을 경우 예외 처리
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
    }
}
