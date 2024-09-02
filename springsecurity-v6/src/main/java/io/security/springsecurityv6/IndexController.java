package io.security.springsecurityv6;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/loginProc")
    public String loginProc() {
        return "loginProc";
    }

    @GetMapping("/failed")
    public String failed() {
        return "failed";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/csrf")
    public String csrf() {
        return "csrf 적용";
    }

}
