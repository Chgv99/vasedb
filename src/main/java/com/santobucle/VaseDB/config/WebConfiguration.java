package com.santobucle.VaseDB.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final ApiKeyInterceptor apiKeyInterceptor;

    private final String clientUrl;

    public WebConfiguration(ApiKeyInterceptor apiKeyInterceptor, @Value("${vasedb.client.url}") String clientUrl) {
        this.apiKeyInterceptor = apiKeyInterceptor;
        this.clientUrl = clientUrl;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // registry.addMapping("/**")
        //         .allowedOrigins("*") // Allow all origins
        //         .allowedMethods("*");
        registry.addMapping("/**")
                .allowedOrigins(
                    "http://localhost:50000",       // Adjust to match Unity editor's origin (TODO: REMOVE BEFORE DEPLOYING)
                        "http://127.0.0.1:50000",              // Adjust to match Unity editor's origin (TODO: REMOVE BEFORE DEPLOYING)
                        clientUrl)
                .allowedMethods("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiKeyInterceptor).addPathPatterns("/api/**");
    }
}
