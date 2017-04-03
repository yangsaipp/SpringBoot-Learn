package com.example;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@Value("${name}")
	private String name;
	
	@RequestMapping("/hello")
	public String helloHtml(Map<String, Object> map) {
		map.put("hello", "from HelloController.helloHtml");
		System.out.println(name);
		map.put("name", name);
		return "hello";
	}
	
	@RequestMapping("/home")
	public String index(Map<String, Object> map) {
		map.put("name", name);
		return "index";
	}
}
