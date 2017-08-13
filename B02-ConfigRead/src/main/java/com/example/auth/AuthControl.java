package com.example.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthControl {
	@Autowired
	private AutherRegistry autherRegistry;
	
	@GetMapping("authList")
	public Object list() {
		List<IAuther> lstAuther = autherRegistry.getAllAuther();
		for(IAuther auther : lstAuther){
			auther.auth();
		}
		return lstAuther.size();
	}
}
