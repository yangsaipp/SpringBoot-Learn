package com.example.handlerMapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;

import com.example.Interceptor.MyInterceptor1;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport{
 
//    @Override
//    @Bean
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
//        handlerMapping.setOrder(0);
//        handlerMapping.setInterceptors(getInterceptors());
//        return handlerMapping;
//    }

	@Bean
    public SimpleServletHandlerAdapter SimpleServletHandlerAdapter() {
        return new SimpleServletHandlerAdapter();
    }

	
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/api", "/v1/*");
		super.addInterceptors(registry);
	}
}