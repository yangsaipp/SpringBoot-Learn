package com.example;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 测试
 * 
 * @authorAngel(QQ:412887952)
 * 
 * @version v.0.1
 * 
 */

@Controller
public class HelloController {

	// 从 application.properties 中读取配置，如取不到默认值为HelloShanhy

	/** FIXME */
	@Value("${application.hello:Hello Angel}")
	private String hello;

	/**
	 * @param map map
	 * @return String
	 */
	@RequestMapping("/helloJsp")
	public String helloJsp(Map<String, Object> map) {

		System.out.println("HelloController.helloJsp().hello=" + hello);

		map.put("hello", hello);

		return "helloJsp";
	}
	
	/**
	 * @param map map
	 * @return String
	 */
	@RequestMapping("/helloJsp2")
	public Object helloJsp2(Map<String, Object> map) {

		System.out.println("HelloController.helloJsp().hello=" + hello);

		map.put("hello", hello);
		ModelAndView mv = new ModelAndView("helloJsp.jsp", map);
		return mv;
	}

}