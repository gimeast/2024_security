package io.security.springsecurityv6;

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
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/invalidSessionUrl", "/expiredUrl").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .sessionManagement(session -> session
                                .invalidSessionUrl("/invalidSessionUrl")
                                .maximumSessions(1)
                                .maxSessionsPreventsLogin(false) //default = false`, false는 새로운 세션이 생성되면 처음 만들어진 세션을 만료 시킨다.
//                                .maxSessionsPreventsLogin(true) //true는 새로운 세션을 생성하려 하면 차단된다.
                                .expiredUrl("/expiredUrl")
                )
        ;

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //yml에서 설정한 정보와 클래스에 설정한 정보가 중복된다면 클래스에 설정한 정보가 우선 적용 된다.
        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }

}
