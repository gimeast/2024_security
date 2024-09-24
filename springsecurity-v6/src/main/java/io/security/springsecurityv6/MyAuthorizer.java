package io.security.springsecurityv6;

import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.stereotype.Component;

@Component
public class MyAuthorizer {

    public boolean isUser(MethodSecurityExpressionOperations root) {
        return root.hasAuthority("ROLE_USER");
    }
}
