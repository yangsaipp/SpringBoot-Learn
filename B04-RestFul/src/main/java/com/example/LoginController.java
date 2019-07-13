/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市康拓普信息技术有限公司开发研制。未经本公司正式书面同意，其他任何个人、
 * 团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/

package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.handlerMapping.ApiVersion;
import com.example.model.Test;
import com.example.model.User;

/**
 * 
 */
@RestController
public class LoginController {

	/**
	 * 日志
	 */
	static Logger log = LoggerFactory.getLogger(LoginController.class);
	Test test;
	
	/**
	 * add RouteConfigVO
	 * @param routeConfigVO routeConfigVO
	 * @return routeConfigVO
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
		System.out.println("login");
		User u = User.get(user.getName(), user.getPassword());
		Map<String,Object> map = new HashMap<>();
		if(u != null) {
			System.out.println("账号密码正确，登陆成功");
			request.getSession().setAttribute("c_user", u);
			String token = request.getSession().getId(); 
			request.getSession().setAttribute(token, new Object());
			map.put("code", "0");
			map.put("message", "账号密码正确，登陆成功");
			map.put("user", u);
			Cookie cookie = new Cookie("x-auth-token", token);
			response.addCookie(cookie);
			return map;
		}else {
			System.out.println("账号密码不对，登陆失败");
			map.put("code", "-1");
			map.put("message", "账号密码不对，登陆失败");
			Cookie cookie = new Cookie("x-auth-token", "-1");
			response.addCookie(cookie);
			return map;
		}
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public Object pageList(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "perPage", defaultValue = "100") int perPage,  HttpServletRequest request){
		System.out.println("sessionId:" + request.getSession().getId());
		Map<String,Object> map = new HashMap<>();
		map.put("total", User.map.size());
		map.put("data",  User.map.values());
		return map;
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public Object saveUser(@RequestBody User user){
		user.setId(String.valueOf((new Random().nextInt())));
		User.save(user);
		return user;
	}
	
}
