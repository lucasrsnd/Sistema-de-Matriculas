package com.TechFrotas.TechFrotas; // Pacote base da aplicação

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite requisições para todos os endpoints
                .allowedOrigins("http://127.0.0.1:5500") // Permite a origem do frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Permite todos os métodos HTTP
    }
}