package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class User {
	@Value("${name}")
	private String name;
	
	@Value("${age}")
	private Integer age;
	
	@Value("${child.name}")
	private String childName;
	
	@Value("${child.age}")
	private Integer childAge;
	
//	无法注入数组	
//	@Value("${hobby}")
//	private List<String> hobby;
	
	@Value("${hobby[0]}")
	private String hobby0;
	
	@Value("${hobby[1]}")
	private String hobby1;
	
	// 使用SpEL
	@Value("#{'${phone}'.split(',')}")
	private List<String> phone;
	
	@Override
	public String toString() {
		return name + " : " + age + "<br/>" 
        		+ childName + " : " + childAge + "<br/>" 
        		+ "hobby : " + hobby0 + " " + hobby1 + "<br/>"
        		+ "phone : " + phone.get(0) + " " + phone.get(1);
	}
}
