package com.easyoops.common.component;

import com.easyoops.biz.member.entity.Member;
import com.easyoops.biz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Service
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;

    // AuthenticationManager.authenticate() 매서드가 실행될 때 호출
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmailId(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("There is no member with this email : " + username));
    }

    // Member 정보를 기반으로 UserDetails 객체 생성
    private UserDetails createUserDetails(Member member) {
        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(Arrays.asList(member.getRole().getId()).toArray(new String[0]))
                .build();
    }
}
