package hello.lottozip.user.controller;

import hello.lottozip.user.dto.LoginRequestDTO;
import hello.lottozip.user.dto.LoginResponseDTO;
import hello.lottozip.user.entity.User;
import hello.lottozip.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public String login(LoginRequestDTO loginRequest, Model model) {
        Optional<User> optionalUser = userRepository.findById(loginRequest.getUserId());

        if (optionalUser.isEmpty()) {
            model.addAttribute("error", "존재하지 않는 사용자입니다.");
            return "login"; // login.html로 돌아감
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(loginRequest.getUserPwd(), user.getUserPwd())) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "login";
        }
        return "redirect:/";
    }
}
