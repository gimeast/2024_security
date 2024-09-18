package io.security.springsecurityv6;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("cws")
public class CustomWebSecurity {

    public boolean check(Authentication authentication, HttpServletRequest request) {
        return authentication.isAuthenticated();
    }
}
