package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.config.TestConfigVO;

@Controller
@SpringBootApplication
public class DemoApplication {
	
	@Autowired
	private User user;
	
	@Autowired
	private MyConfig myConfig;
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
	
	@RequestMapping("/user")
    @ResponseBody
    String user() {
        return user.toString();
    }

	@RequestMapping("/myConfig")
	@ResponseBody
	String myConfig() {
		return myConfig.toString();
	}
	
	
//	@Bean
//	public TestConfigVO testConfigVO() {
//		TestConfigVO test = new TestConfigVO();
//		System.out.println("Test Application :" + test);
//		return test;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
