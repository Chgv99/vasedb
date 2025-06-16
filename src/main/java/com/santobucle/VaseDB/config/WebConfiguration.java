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
        registry.addMapping("/**")
                .allowedOrigins("https://" + clientUrl)
                .allowedMethods("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiKeyInterceptor).addPathPatterns("/api/**");
    }
}
