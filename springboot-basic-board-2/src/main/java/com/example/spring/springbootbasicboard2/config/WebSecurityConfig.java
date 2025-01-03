package com.example.spring.springbootbasicboard2.config;

import com.example.spring.springbootbasicboard2.config.filter.TokenAuthenticationFilter;
import com.example.spring.springbootbasicboard2.config.security.CustomAuthenticationFailureHandler;
import com.example.spring.springbootbasicboard2.config.security.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        "/static/**", "/css/**", "/js/**"
                );
    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(
                                        new AntPathRequestMatcher("/member/login"),
                                        new AntPathRequestMatcher("/member/join"),
                                        new AntPathRequestMatcher("/join"),
                                        new AntPathRequestMatcher("/login"),
                                        new AntPathRequestMatcher("/"),
                                        new AntPathRequestMatcher("/write"),
                                        new AntPathRequestMatcher("/detail"),
                                        new AntPathRequestMatcher("/update/**"),
                                        new AntPathRequestMatcher("/api/board/file/download/**"),
                                        new AntPathRequestMatcher("/access-denied")

                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) //JWT필터 추가
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(accessDeniedHandler()) // 403
                        .authenticationEntryPoint(authenticationEntryPoint()) // 401
                )
        ;


        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/access-denied");
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.sendRedirect("/access-denied");
        };
    }

}