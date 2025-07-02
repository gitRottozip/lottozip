package hello.lottozip.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.GenericFilterBean;

/*
* 사용자가 요청을 보낼 때. 요청 헤더에 포함된 JWT 토큰을 검사하고 인증 처리하는 역할*/

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

}
