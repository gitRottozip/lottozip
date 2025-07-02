package hello.lottozip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
* 시큐리티 컨피그 역할
* - 어떤 요청을 허용할지
* - 어떤 요청에 인증을 요구할지
* - 로그인 페이지와 로그인 처리 방식
* - 로그아웃 처리
* - CSRF 설정 <- 사이트 위조 요청 방지*/

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/css/**", "/js/**", "/img/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/signup", "/signup/**", "/main", "/component/**", "/header/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/signup").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // 회원가입 페이지 url
                        .defaultSuccessUrl("/main")
                        .permitAll() //회원가입 페이지는 누구나 접근 가능
                )
                .logout(logout -> logout.permitAll()) //로그아웃은 누구나 가능
                .csrf(csrf -> csrf.disable()); //CRSF 보호 비활성화

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
