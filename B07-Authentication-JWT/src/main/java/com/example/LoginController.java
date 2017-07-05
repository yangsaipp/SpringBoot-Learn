/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市康拓普信息技术有限公司开发研制。未经本公司正式书面同意，其他任何个人、
 * 团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/

package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

/**
 * 
 */
@RestController
public class LoginController {

	private String key = "ys_test";
	
	/**
	 * 日志
	 */
	static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	private String createToken(User user) {
		Date iat = new Date();	// 签发时间
		Date nbf = new Date();	// 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getId());
		String compactJws = Jwts.builder()
		  .setClaims(map)
		  .setSubject(user.getName())
		  .setIssuedAt(iat)
		  .setNotBefore(nbf)
		  .setId(UUID.randomUUID().toString())
		  .signWith(SignatureAlgorithm.HS512, key)
		  .compact();
		return compactJws;
	}
	
	/**
	 * add RouteConfigVO
	 * @param routeConfigVO routeConfigVO
	 * @return routeConfigVO
	 */
	@RequestMapping(value = "/api/account", method = RequestMethod.GET)
	public Object account(@RequestHeader("x-auth-token") String token){
		System.out.println("===" + token);
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		System.out.println(claims.getId());
		String userId = (String) (claims.get("userId"));
		return User.get(userId);
	}
	
	/**
	 * add RouteConfigVO
	 * @param routeConfigVO routeConfigVO
	 * @return routeConfigVO
	 */
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public Object login(@RequestBody User user){
		System.out.println("login");
		User u = User.get(user.getAccount(), user.getPassword());
		if(u != null) {
			String token = createToken(u);
			return ResponseEntity.ok().body(token);
		}else {
			return ResponseEntity.status(HttpStatus.valueOf(401)).build();
		}
	}
	
	/**
	 * 登出
	 */
	@RequestMapping(value = "/loginOut", method = RequestMethod.DELETE)
	public Object loginOut() {
		return null;
	}
	
	/**
	 * 刷新
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.PUT)
	public Object refresh() {
		return null;
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
