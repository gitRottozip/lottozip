package hello.lottozip.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CommonController {

    @GetMapping("header")
    public String header(){
        return "/component/header";
    }

    @GetMapping("side")
    public String side(){
        return "/component/side";
    }
}
