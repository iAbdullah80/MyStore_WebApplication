package com.example.shop.config;

import com.example.shop.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JwtTokenFilter jwtTokenFilter(TokenService tokenService) {
        return new JwtTokenFilter(tokenService);
    }

}
