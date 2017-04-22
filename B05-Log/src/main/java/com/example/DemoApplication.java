package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class DemoApplication {
	
	Logger log = LoggerFactory.getLogger(DemoApplication.class);
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
		// JVM 参数配置为 -Dspring.profiles.active=dev才输出
		log.debug("home");
		System.out.println("sysout:home");
        return "Hello World!";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
