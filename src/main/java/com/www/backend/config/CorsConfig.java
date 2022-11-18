package com.www.backend.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // TODO: 추후에 프론트의 엔드포인트가 특정되면 수정할 예정
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
