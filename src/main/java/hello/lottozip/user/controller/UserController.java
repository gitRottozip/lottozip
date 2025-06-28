package hello.lottozip.user.controller;

import hello.lottozip.user.dto.SignUpDTO;
import hello.lottozip.user.entity.User;
import hello.lottozip.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String mainPage(){
        return "main";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html 템플릿
    }


    @GetMapping("/signup")
    public String signUpPage(Model model){
        model.addAttribute("signUpDTO", new SignUpDTO());
        return "SignUp";
    }

    @PostMapping("/signup")
    public String signUpUser(SignUpDTO signUpDTO){
        System.out.println("컨트롤러 진입: " + signUpDTO);
        User savedUser = userService.signUp(signUpDTO);
        System.out.println("서비스 완료, 저장된 유저: " + savedUser);
        return "redirect:/main";
    }
}
