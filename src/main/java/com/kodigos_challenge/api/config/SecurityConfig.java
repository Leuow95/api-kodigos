package com.kodigos_challenge.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request)-> request
                        .requestMatchers(HttpMethod.POST, "/auth/**")
                        .permitAll()
//                        .requestMatchers(HttpMethod.POST, "/order/**")
//                        .authenticated()
                        .anyRequest().permitAll()
                ).oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> {}));

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return jwtUtil.jwtDecoder();
    }

}