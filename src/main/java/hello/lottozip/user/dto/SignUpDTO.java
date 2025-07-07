package hello.lottozip.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    @NotBlank(message = "아이디는 필수 입력입니다.")
    private String userId;
    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    private String userPwd;
    @NotBlank(message = "이름은 필수 입력입니다.")
    private String userName;
    @Past(message = "생년월일은 과거 날짜여야 합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userBirth;
    @NotBlank(message = "전화번호는 필수 입력입니다.")
    private String userPhone;
    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String userEmail;
}
