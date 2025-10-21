package com.jjang051.board02.config;

import com.jjang051.board02.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(
                        "/member/info",
                        "/member/delete",
                        "/member/logout",
                        "/board/write",
                        "/board/delete",
                        "/board/*/detail",
                        "/board/**/detail",
                        "/board/edit"
                        )
                .excludePathPatterns(
                        "/member/login",
                        "/member/signup",
                        "/member/idCheck",
                        "/css/**","/js/**","/imsges/**"
                );
    }
}
