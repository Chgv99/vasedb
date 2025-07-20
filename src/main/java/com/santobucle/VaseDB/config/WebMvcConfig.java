package com.santobucle.VaseDB.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final OriginValidationInterceptor originInterceptor;

    public WebMvcConfig(OriginValidationInterceptor originInterceptor) {
        this.originInterceptor = originInterceptor;
    }

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //             .allowedOrigins("https://" + clientUrl)
    //             // .allowedOriginPatterns("*")
    //             .allowedMethods("*");
    // }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
            // .addInterceptor(apiKeyInterceptor).addPathPatterns("/api/**");
            .addInterceptor(originInterceptor).addPathPatterns("/auth/**");
    }
}
