package com.example.demospringboot.config;

import com.example.demospringboot.Interceptor.SignatureInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private SignatureInterceptor getSignature;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器,多个则registry另起一个
        registry.addInterceptor(getSignature).addPathPatterns("/**");
    }
}
