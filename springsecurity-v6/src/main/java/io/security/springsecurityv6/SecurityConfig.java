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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //기본은 세션저장
        
        //쿠키저장방식
//        CookieCsrfTokenRepository csrfTokenRepository = new CookieCsrfTokenRepository();
//        XorCsrfTokenRequestAttributeHandler csrfTokenRepository = new XorCsrfTokenRequestAttributeHandler();
//        csrfTokenRepository.setCsrfRequestAttributeName(null); //지연방식으로 사용하지않으려면 null을 주면 된다.

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/csrf", "/csrf-token").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
//                .csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository))
//                .csrf(csrf -> csrf.csrfTokenRequestHandler(csrfTokenRepository)) //XorCsrfTokenRequestAttributeHandler는 default로 구현안해도 되지만 커스텀 하려면 알고있어야 한다.
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
