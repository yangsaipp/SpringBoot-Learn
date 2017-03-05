package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
