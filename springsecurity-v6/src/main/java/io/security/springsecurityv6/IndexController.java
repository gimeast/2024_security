package io.security.springsecurityv6;

import io.micrometer.common.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index(String customParam) {
        if(StringUtils.isNotEmpty(customParam)) {
            return "custom";
        } else {
            return "index";
        }
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

    @GetMapping("/logoutSuccess")
    public String logoutSuccess() {
        return "logoutSuccess";
    }

}
