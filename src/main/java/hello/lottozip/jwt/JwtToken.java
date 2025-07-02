package hello.lottozip.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {
    private String grantType; //Bearer 방식
    private String accessToken; //로그인 인증용 JWT
    private String refreshToken; //엑세스 토큰 재발급용 JWT
}
