package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Configuration
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {
	
	@Autowired
    private ApplicationContext appContext;
	
	@RequestMapping("/")
    @ResponseBody
    String home() throws Throwable {
//		 String[] beans = appContext.getBeanDefinitionNames();
//        Arrays.sort(beans);
//        for (String bean : beans) {
//            System.out.println(bean);
//        }
		System.out.println(Thread.currentThread().getName() + " ： hello");
		Thread.sleep(10000);
        return "Hello World!";
    }

	@RequestMapping("/loginSuccess")
	@ResponseBody
	String loginSuccess(@RequestBody Map<String, Object> request) throws Throwable {
//		System.out.println(Thread.currentThread().getName() + " ： hello");
//		Thread.sleep(3000);
		System.out.println(request);
		return "Hello World!";
	}
	
	 // 使用外部Tomcat7+运行时通过该方法初始化SpringBoot
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	System.out.println("Servlet容器加载");
        return application.sources(DemoApplication.class);
    }
	
    @RequestMapping("/getData1")
	@ResponseBody
	Object getData1() throws Throwable {
    	List<Map<String, String>> objList = new ArrayList<>();
		Map<String, String> data = new HashMap<String, String>();
    	data.put("text", "年");
    	data.put("value", "year");
    	objList.add(data);
    	
    	Map<String, String> data2 = new HashMap<String, String>();
    	data2.put("text", "月");
    	data2.put("value", "month");
    	objList.add(data2);
		return objList;
	}
    
    @RequestMapping("/getData2")
	@ResponseBody
	Object getData2(HttpServletRequest request) throws Throwable {
    	String type = request.getParameter("type");
    	Map<String, String> data = new HashMap<String, String>();
    	if("year".equals(type)) {
    		data.put("date", "2020");
    	} else if("month".equals(type)) {
    		data.put("date", "2020-10");
    	}
		return data;
	}
     
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
