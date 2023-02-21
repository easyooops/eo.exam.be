package com.easyoops.common.config;

import com.easyoops.common.filter.JwtAuthenticationFilter;
import com.easyoops.ext.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                // rest api 이므로 basic auth, csrf 사용 해제
        http.httpBasic().disable()
                .csrf().disable()
                // 세션 미사용 설정
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // login에 대한 요청은 모두 허용
                .antMatchers("/members/login").permitAll()
                // USER 권한이 있어야 요청 허용
                .antMatchers("/members/test").hasRole("USER")
                .antMatchers("/error").permitAll()
                // 이외 모든 요청에 대한 인증 필요
                .anyRequest().authenticated()
                .and()
                // jwt filter를 먼저 적용
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // SecurityFilterChain 이전에 실행 되어 인증 절차를 생략 하고자 하는 경로 명시
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // h2 db console, swagger ui console
        // static resource (css, js, images, webjars, favicon)
        // 접근 filter 제외
        return web -> web.ignoring()
                .antMatchers("/swagger-ui/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/v2/api-docs/**")
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
