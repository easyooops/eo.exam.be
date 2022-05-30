package com.easyoops.common.config;

// ready....

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/about").authenticated()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/user/loginView")
//                .successForwardUrl("/index")
//                .failureForwardUrl("/index")
//                .permitAll()
//                .and()
//                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//}
