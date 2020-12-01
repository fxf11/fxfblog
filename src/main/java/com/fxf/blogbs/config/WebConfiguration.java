package com.fxf.blogbs.config;

import com.fxf.blogbs.interceptors.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.concurrent.Executors;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private InterceptorConfig interceptorConfig;

    public WebConfiguration(InterceptorConfig interceptorConfig) {
        this.interceptorConfig = interceptorConfig;
    }

//    设置跨域请求
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer){
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorConfig())
                .addPathPatterns("/**")         //其他接口token验证
                .excludePathPatterns("/user/login","/blog/**","/sort/**","/comment/**",
                        "/dist/**","/*.html","/**/*.css","/**/*.woff","/**/*.js","/**/*.jpg",
                        "/**/*.ttf","/**/*.woff2","/**/*.eot","/**/*.png");  //放行的请求
//        WebMvcConfigurer.super.addInterceptors(registry);

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
