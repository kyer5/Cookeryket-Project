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

@Slf4j  // 로깅에 대한 추상 레이어를 제공하는 인터페이스 모음, 로깅이 필요한 부분에는 log 변수로 로그 생성하면 됨
@RestController  // 컨트롤러 선언 (@Controller와 @ResponseBody가 합쳐진 것) 주로 JSON 형태로 데이터를 클라이언트에게 반환
@RequestMapping("/user")  // 특정 URI에 대한 요청을 처리하는 메소드
@RequiredArgsConstructor  // final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어줌
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")  // URL에 변수(데이터)를 노출하지 않고 요청, 데이터를 Body에 포함, URL에 데이터가 노출되지 않음
    public MemberEntity signup(@RequestBody MemberSignupDTO memberSignupDTO) {
        log.info("signupDTO = {}", memberSignupDTO);
        Optional<MemberEntity> joinMember = memberService.save(memberSignupDTO);
        log.info("Member = {}", joinMember);
        return joinMember.get();
    }


    // 로그인
    @PostMapping("/login")  // URL에 변수를 포함시켜 요청, 데이터를 Header에 포함하여 전송
    public void signIn(@RequestBody MemberLoginDTO memberLoginDTO) {
        memberService.memberLogin(memberLoginDTO);
    }


    // 회원 정보 수정
    @PutMapping("/update")
    public void updateMember(@RequestBody MemberUpdateDTO memberUpdateDTO) {
        memberService.memberUpdate(memberUpdateDTO);
    }


    // 회원 정보 삭제
    @DeleteMapping("/delete")
    public void deleteMember(@RequestBody MemberDeleteDTO memberDeleteDTO) {
        memberService.memberDelete(memberDeleteDTO);
    }
}
