package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
//@EnableTemplateVisit
public class DemoApplication extends SpringBootServletInitializer {
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
	
	 // 重写configure方法，让Servlet容器加载时配置应用
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	直接使用Spring的SimpleUrlHandlerMapping实现模板直接访问
//	@Bean
//	public SimpleUrlHandlerMapping sampleServletMapping() {
//	    SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//	    mapping.setOrder(Integer.MAX_VALUE - 2);
//
//	    Properties urlProperties = new Properties();
//	    urlProperties.put("/**/*.ht", new TemplateVisitHandler());
//
//	    mapping.setMappings(urlProperties);
//
//
//	    return mapping;
//	}
}
