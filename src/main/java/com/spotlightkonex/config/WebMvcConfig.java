package com.spotlightkonex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //모든 경로에 대해서
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("*") // 기타 설정
                .allowedHeaders("*");
    }
}