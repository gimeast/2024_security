package io.security.springsecurityv6.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/logoutSuccess").permitAll() //logoutSuccess에 모두 접근가능하도록 허용한다는 의미
                        .anyRequest().authenticated()) //어떤 요청이든 인증하겠다는 의미
                .formLogin(Customizer.withDefaults()) //기본적으로 주어지는 폼로그인을 사용하겠다는 의미

//                .csrf(csrf -> csrf.disable())
                .logout(logout -> logout
                        .logoutUrl("/logoutProc")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logoutProc", "GET")) //logoutUrl과 RequestMatcher가 동시에 있으면 RequestMatcher이 더 우선시 된다

                        .logoutSuccessUrl("/logoutSuccess")
                        .logoutSuccessHandler(new LogoutSuccessHandler() {
                            @Override
                            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                response.sendRedirect("/logoutSuccess");
                            }
                        })
                        .deleteCookies("JSESSIONID", "remember-me")
                        .invalidateHttpSession(true) //logout시 세션 무효화
                        .clearAuthentication(true) //security context안에 있는 authentication을 제거한다.
                        .addLogoutHandler(new LogoutHandler() {
                            @Override
                            public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                                HttpSession session = request.getSession();
                                session.invalidate();
                                SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(null);
                                SecurityContextHolder.getContextHolderStrategy().clearContext();
                            }
                        })
                        .permitAll()
                );


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //yml에서 설정한 정보와 클래스에 설정한 정보가 중복된다면 클래스에 설정한 정보가 우선 적용 된다.
        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }

}
