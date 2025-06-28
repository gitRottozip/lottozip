package hello.lottozip.user.service;

import hello.lottozip.user.dto.SignUpDTO;
import hello.lottozip.user.entity.Role;
import hello.lottozip.user.entity.User;
import hello.lottozip.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service
@RequiredArgsConstructor //자동으로 의존성 주입을 할 수 있어서 @Autowired를 안쓸 수 있음
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    //회원가입
    @Transactional
    public User signUp(SignUpDTO signUpDTO){
        User user = User.builder()
                .userId(signUpDTO.getUserId())
                .userPwd(passwordEncoder.encode(signUpDTO.getUserPwd()))
                .userName(signUpDTO.getUserName())
                .userBirth(signUpDTO.getUserBirth())
                .userPhone(signUpDTO.getUserPhone())
                .userEmail(signUpDTO.getUserEmail())
                .userRole(Role.USER)
                .build();
        return userRepository.save(user);
    }
}
