package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MvcInterceptorConfig
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/9
 * @Version V1.0
 **/
@Configuration
public class MvcInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new MvcInterceptor());

    }


}
