package com.example.blog.config;

import com.example.blog.interceptor.BlogIdempotentInterceptor;
import com.example.blog.interceptor.LoginInterceptor;
import com.example.blog.interceptor.TypeIdempotentInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    BlogIdempotentInterceptor blogidempotentInterceptor() {
        return new BlogIdempotentInterceptor();
    }

    @Bean
    TypeIdempotentInterceptor typeidempotentInterceptor() {
        return new TypeIdempotentInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**").excludePathPatterns("/admin/login")//todo:拦截器会拦截/admin/login
                .excludePathPatterns("/admin");
        registry.addInterceptor(blogidempotentInterceptor())
                .addPathPatterns("/admin/blogs/**");
        registry.addInterceptor(typeidempotentInterceptor())
                .addPathPatterns("/admin/types/**");
    }
}
