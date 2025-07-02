package hello.lottozip.jwt;

import org.springframework.security.core.userdetails.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key;

    /*
    * yml에서 base64로 인코딩된 비밀키를 읽어서, JWT 서명에 사용할 Key 객체로 변환*/
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey){ //yml에 secret코드 주입
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes); //키 객체를 이 클래스의 필드에 저장해서 JWT 토큰 만들거나 검증할 때 재사용하게 저장
    }

    /* JWT 토큰 생성 메서드 */
    public JwtToken generateToken(Authentication authentication){ //사용자의 Authentication 정보를 바탕으로 JWT 토큰을 만들어 줌
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); //유저의 권한 목록을 콤마로 이어 붙임
        long now = (new Date()).getTime();

        Date accessTokenExpiresln = new Date(now + 86400000); // 토큰 만료 시간 설정 1일

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName()) //사용자 식별자
                .claim("auth", authorities) //권한 정보
                .setExpiration(accessTokenExpiresln) //만료 시간
                .signWith(key, SignatureAlgorithm.HS256) // 서명
                .compact();

        //Refresh 토큰 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + 86400000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /*인증 객체 만들기
    * jwt에서 정보를 꺼내서 Authentication 객체로 만들어줌
    * JWT를 복호화해서 권한 정보를 꺼내고, 그걸로 UserDetails 객체 생성
    * 최종적으로 UsernamePasswordAuthenticationToken을 리턴해서 SpringSecurity 인증 처리에 사용*/
    public Authentication getAuthentication(String accessToken){
        Claims claims = parseClaims(accessToken);

        if(claims.get("auth") == null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        //클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication return
        // UserDetails: interface, User: UserDetails를 구현한 class
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }



    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
