package hello.lottozip.user.userService;

import hello.lottozip.user.dto.SignUpDTO;
import hello.lottozip.user.entity.User;
import hello.lottozip.user.repository.UserRepository;
import hello.lottozip.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signUp_shouldSaveUser() {
        // given
        SignUpDTO dto = new SignUpDTO();
        dto.setUserId("testuser");
        dto.setUserPwd("password");
        dto.setUserName("테스트");
        dto.setUserBirth(new Date());
        dto.setUserPhone("01012345678");
        dto.setUserEmail("test@example.com");

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        User savedUser = userService.signUp(dto);

        // then
        assertEquals("testuser", savedUser.getUserId());
        assertEquals("encodedPassword", savedUser.getUserPwd());
        assertEquals("테스트", savedUser.getUserName());
        assertEquals("01012345678", savedUser.getUserPhone());
        assertEquals("test@example.com", savedUser.getUserEmail());

        verify(passwordEncoder, times(1)).encode("password");
        verify(userRepository, times(1)).save(any(User.class));
    }
}
