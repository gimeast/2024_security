package io.security.springsecurityv6.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) //어떤 요청이든 인증하겠다는 의미
//              .formLogin(Customizer.withDefaults()); //기본적으로 주어지는 폼로그인을 사용하겠다는 의미
                .formLogin(form -> form
//                        .loginPage("/loginPage")
                        .loginProcessingUrl("/loginProc")
                        .defaultSuccessUrl("/", false) //true=인증에 성공하면 무조건 이곳으로 이동한다. false=인증이 필요한 곳을 방문 하였으면 인증을 완료 후 방문하려는 위치로 리다이렉트된다.
                        .failureUrl("/failed")
                        .usernameParameter("userId")
                        .passwordParameter("passwd")
                        .successHandler(new AuthenticationSuccessHandler() { //failureUrl을 무시하고 해당 핸들러를 우선시한다.
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                System.out.println("authentication success : " + authentication);
                                response.sendRedirect("/home");
                            }
                        })
                        .failureHandler(new AuthenticationFailureHandler() { //defaultSuccessUrl을 무시하고 해당 핸들러를 우선시한다.
                            @Override
                            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                                System.out.println("exception: " + exception.getMessage());
                                response.sendRedirect("/login");
                            }
                        })
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //yml에서 설정한 정보와 클래스에 설정한 정보가 중복된다면 클래스에 설정한 정보가 우선 적용 된다.
//        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
//        return new InMemoryUserDetailsManager(user);
//    }

}
