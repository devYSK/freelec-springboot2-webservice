package com.ys.kr.springboot.config.auth;

import com.ys.kr.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-consoLe 화면을사용하기 위해 해당옵션들을 disable 합니다.
                .and()
                    .authorizeRequests()  //URL별권한관리를 설정하는 옵션의시작점. authorizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있습니다
                    .antMatchers("/", "/css/**", "/images/**",
                            "/js/**", "/h2-console/**", "/profile").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 권한 관리 대상을 지정하는 옵션입니다. URL, HTTP 메소드별로 관리 가능
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/") //로그아웃 기능에 대한 여러 설정의 진입점입니다. 로그아웃 성공시 '/' 주소로 이동
                .and()
                    .oauth2Login()
                        .userInfoEndpoint() // OAuth 2 로그인 기능에 대한여러 설정의 진입점입니다
                            .userService(customOAuth2UserService); //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                                                                    //리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고
                                                                    //자 하는 기능을 명시할 수 있습니다.

        super.configure(http);

    }
}
