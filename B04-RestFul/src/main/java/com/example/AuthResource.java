package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

@RestController
public class AuthResource {
	
	@GetMapping("/session")
	public Object login(String username, String password) {
		System.out.println("userName:" + username);
		System.out.println("password:" + password);
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		return "token";
	}
	
	@DeleteMapping("/session")
	public Object logout() {
		System.out.println("logout:");
		return "success";
	}
	
	@GetMapping("/currentUser")
	public Object currentUser() {
		Map<String, User> map = new HashMap<String, User>();
		map.put("1", new User("1","张三","123","1"));
		map.put("2", new User("2","李四","123","1"));
		return map.values();
	}
	
	@GetMapping("/currentAccount")
	public Object currentAccount() {
		System.out.println("currentUser");
		return new User("1","张三","123","1");
	}
}
