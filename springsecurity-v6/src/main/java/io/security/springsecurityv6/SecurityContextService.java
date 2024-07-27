package io.security.springsecurityv6;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextService {

    public void securityContext() {
        SecurityContextHolderStrategy contextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
        SecurityContext context = contextHolderStrategy.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println("authentication = " + authentication);

    }
}