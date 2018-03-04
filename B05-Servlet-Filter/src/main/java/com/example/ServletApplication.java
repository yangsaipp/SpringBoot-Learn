package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class ServletApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}
	
	@PostMapping("/hw")
	@ResponseBody
	String home2() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}
}
