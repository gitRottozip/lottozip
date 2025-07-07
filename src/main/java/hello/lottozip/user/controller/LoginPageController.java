package hello.lottozip.user.controller;

import hello.lottozip.user.dto.LoginRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginRequestDTO", new LoginRequestDTO());
        return "login"; // templates/login.html
    }
}
