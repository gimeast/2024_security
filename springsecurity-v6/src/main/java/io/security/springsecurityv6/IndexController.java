package io.security.springsecurityv6;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
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

    @GetMapping("/csrf-token")
    public String csrfToken(HttpServletRequest request) {
        CsrfToken csrfToken1 = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        CsrfToken csrfToken2 = (CsrfToken) request.getAttribute("_csrf");
        String token = csrfToken1.getToken() + "\r\n" + csrfToken2.getToken();
        return token;
    }

}
