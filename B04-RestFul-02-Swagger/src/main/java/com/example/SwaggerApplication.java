package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@Configuration
@SpringBootApplication
public class SwaggerApplication extends SpringBootServletInitializer {
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
		System.out.println("hello");
        return "Hello World!";
    }
	
	 // 使用外部Tomcat7+运行时通过该方法初始化SpringBoot
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	System.out.println("Servlet容器加载");
        return application.sources(SwaggerApplication.class);
    }
	
    
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
    	 SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
         requestFactory.setConnectTimeout(1000);
         requestFactory.setReadTimeout(1000);
    	return requestFactory;
    }
    
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        return new RestTemplate(clientHttpRequestFactory);
    }

    
	public static void main(String[] args) {
		SpringApplication.run(SwaggerApplication.class, args);
	}
}
