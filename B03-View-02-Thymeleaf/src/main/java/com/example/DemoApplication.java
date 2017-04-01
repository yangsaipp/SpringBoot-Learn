package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.handler.MyHandler;
import com.example.handler.MyHandlerAdapter;
import com.example.handler.MyHandlerMapping;

@Controller
@SpringBootApplication
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
	
	@Bean
	public MyHandlerAdapter MyHandlerAdapter() {
		return new MyHandlerAdapter();
	}
	
	@Bean
	public MyHandler myHandler() {
		return new MyHandler();
	}

	@Bean
	public MyHandlerMapping myHandlerMapping(MyHandler handler) {
		MyHandlerMapping mapping = new MyHandlerMapping(handler);
		return mapping;
	}

}
