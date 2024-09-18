package io.security.springsecurityv6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultHttpSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, ApplicationContext context) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(new CustomRequestMatcher("/admin")).hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
        ;

        return http.build();
    }
/*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, ApplicationContext context) throws Exception {
        DefaultHttpSecurityExpressionHandler expressionHandler = new DefaultHttpSecurityExpressionHandler();
        expressionHandler.setApplicationContext(context);

        WebExpressionAuthorizationManager authorizationManager
                = new WebExpressionAuthorizationManager("@cws.check(authentication, request)");

        authorizationManager.setExpressionHandler(expressionHandler);


        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("custom/**").access(authorizationManager)
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
        ;

        return http.build();
    }
*/


/*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/{name}")
                        .access(new WebExpressionAuthorizationManager("#name == authentication.name"))

                        .requestMatchers("admin/db")
                        .access(new WebExpressionAuthorizationManager("hasRole('ROLE_DB') or hasAuthority('ROLE_ADMIN')"))

                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
        ;

        return http.build();
    }
*/

    @Bean
    public UserDetailsService userDetailsService() {
        //yml에서 설정한 정보와 클래스에 설정한 정보가 중복된다면 클래스에 설정한 정보가 우선 적용 된다.
        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        UserDetails manager = User.withUsername("manager").password("{noop}1111").roles("MANAGER").build();
        UserDetails admin = User.withUsername("admin").password("{noop}1111").roles("ADMIN","WRITE").build();
        return new InMemoryUserDetailsManager(user, manager, admin);
    }

}
