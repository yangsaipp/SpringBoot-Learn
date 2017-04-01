/******************************************************************************
* Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.example.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

/**
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年4月1日 杨赛
 */
@RestController
public class UserController {

	
	private Map<String, User> dataMap;
	
	/**
	 * 构造函数
	 */
	public UserController() {
		super();
		dataMap = new HashMap<String, User>();
		User u = new User("1", "张三", 25);
		u.setAccount("zhangsan");
		u.setPassword("123456");
		dataMap.put(u.getId(), u);

		u = new User("2", "李四", 25);
		u.setAccount("lisi");
		u.setPassword("123456");
		
		dataMap.put(u.getId(), u);
	}

	@GetMapping("/users")
	public Object list(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "perPage", defaultValue = "100") int perPage) {
		Map<String, Object> map = new HashMap<>();
		map.put("total", dataMap.size());
		map.put("data", dataMap.values());
		return map;
	}
	
	@GetMapping("/users/{id}")
	public User get(@PathVariable String id) {
		if(dataMap.containsKey(id)) {
			return dataMap.get(id);
		}
		return null;
	}
	
	@PostMapping("/users")
	public User add(@RequestBody User user) {
		user.setId(String.valueOf((new Random().nextInt())));
		dataMap.put(user.getId(), user);
		return user;
	}
	
	@PutMapping("/users/{id}")
	public User update(@PathVariable String id, @RequestBody User user) {
		dataMap.put(id, user);
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public User delete(@PathVariable String id) {
		if(dataMap.containsKey(id)) {
			return dataMap.remove(id);
		}
		return null;
	}
	
	
}
