package io.security.springsecurityv6.config;

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

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) //어떤 요청이든 인증하겠다는 의미
            .formLogin(Customizer.withDefaults()); //기본적으로 주어지는 폼로그인을 사용하겠다는 의미

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //yml에서 설정한 정보와 클래스에 설정한 정보가 중복된다면 클래스에 설정한 정보가 우선 적용 된다.
        UserDetails admin = User.withUsername("admin").password("{noop}password").roles("ADMIN").build();
        UserDetails user1 = User.withUsername("user1").password("{noop}password").roles("USER").build();
        UserDetails user2 = User.withUsername("user2").password("{noop}password").roles("USER").build();
        return new InMemoryUserDetailsManager(admin,user1, user2);
    }

}
