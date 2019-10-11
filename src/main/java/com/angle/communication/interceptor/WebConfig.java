package com.angle.communication.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry){

        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
  //addPathPatterns哪些地址进行拦截
        //excludePathPatterns("/admin/**");哪些地址略过

}}