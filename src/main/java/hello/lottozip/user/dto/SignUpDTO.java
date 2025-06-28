package hello.lottozip.user.dto;

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
    private String userId;
    private String userPwd;
    private String userName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userBirth;
    private String userPhone;
    private String userEmail;
}
