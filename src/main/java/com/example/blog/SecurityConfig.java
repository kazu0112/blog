package com.example.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 実装中の仮設定。すべてのページを公開する（認証はステップ6で本実装する）
        http.authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll());
        return http.build();
    }
}