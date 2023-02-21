package com.easyoops.biz.sample.controller;

import com.easyoops.biz.sample.dto.MemberLoginRequestDto;
import com.easyoops.biz.sample.dto.TokenInfo;
import com.easyoops.biz.sample.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        String emailId = memberLoginRequestDto.getEmailId();
        String password = memberLoginRequestDto.getPassword();
        return memberService.login(emailId, password);
    }
}
