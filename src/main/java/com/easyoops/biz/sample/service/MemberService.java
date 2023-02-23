package com.easyoops.biz.sample.service;

import com.easyoops.biz.sample.dto.MemberLoginRequestDto;
import com.easyoops.biz.sample.dto.TokenInfo;
import com.easyoops.biz.sample.entity.Member;
import com.easyoops.biz.sample.repository.MemberRepository;
import com.easyoops.ext.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenInfo login(MemberLoginRequestDto memberLoginRequestDto) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 인증 여부를 확인하는 초기 authenticated 값은 false
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(memberLoginRequestDto.getEmailId(), memberLoginRequestDto.getPassword());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        return jwtTokenProvider.generateToken(authentication);
    }

    public Member getMemberById(int id) throws Exception {
        return memberRepository.findById(id).orElseThrow(() -> new Exception("There is no member with that id"));
    }
}
