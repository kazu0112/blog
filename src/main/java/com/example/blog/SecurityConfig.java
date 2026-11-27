package com.example.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // 読む機能とログイン画面・CSSは誰でも見られる。それ以外（書く機能）はログイン必須
                .requestMatchers(HttpMethod.GET, "/", "/articles", "/articles/{id:[0-9]+}",
                        "/login", "/css/**", "/error").permitAll()
                .anyRequest().authenticated())
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/articles"))
            .logout(logout -> logout
                .logoutSuccessUrl("/articles"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}