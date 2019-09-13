package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.two.scan.MyScan;

@Controller
@SpringBootApplication
//@MyScan("com")
public class CustomAnnotationApplication {
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(CustomAnnotationApplication.class, args);
	}
}
