package com.santobucle.VaseDB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")  // Allow all origins
                        .allowedMethods("*");
                // registry.addMapping("/**")
                //         .allowedOrigins("http://localhost:50000", "http://127.0.0.1:50000") // Adjust to match Unity editor's origin
                //         .allowedMethods("*");
            }
        };
    }
}
