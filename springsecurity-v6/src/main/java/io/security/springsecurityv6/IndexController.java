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

    @GetMapping("/custom")
    public String custom() {
        return "custom";
    }

    @GetMapping("/admin/db")
    public String admin() {
        return "admin";
    }

    @GetMapping("/api/photos")
    public String photos() {
        return "photos";
    }

    @GetMapping("/oauth/login")
    public String oauth() {
        return "oauthLogin";
    }



}
