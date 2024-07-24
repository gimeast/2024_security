package io.security.springsecurityv6;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
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
    
    @GetMapping("/anonymous")
    public String anonymous() {
        return "anonymous";
    }

    @GetMapping("/authentication")
    public String authentication(Authentication authentication) {
        if(authentication instanceof AnonymousAuthenticationToken) {
            return "anonymous";
        } else {
            return "not anonymous";
        }
    }

    @GetMapping("/anonymous-context")
    public String anonymousContext(@CurrentSecurityContext SecurityContext securityContext) {
        return securityContext.getAuthentication().getName();
    }

}
