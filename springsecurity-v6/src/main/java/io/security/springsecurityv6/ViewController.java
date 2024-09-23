package io.security.springsecurityv6;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/method")
    public String method() {
        return "method";
    }
}