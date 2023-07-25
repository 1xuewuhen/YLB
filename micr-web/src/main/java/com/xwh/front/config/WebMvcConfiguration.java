package com.xwh.front.config;

import com.xwh.front.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 作者:陈方银
 * 时间:2023/7/12
 */

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private TokenCheckPathConfig tokenCheckPathConfig;
    @Resource
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        String[] addPath = {"/v1/user/realName"};
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns(tokenCheckPathConfig.getPath());
    }
}
