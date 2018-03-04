package com.example.auth;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.TestConfigVO;


@RestController
public class AuthControl {
	@Autowired
	private AutherRegistry autherRegistry;

	@Autowired
//	@Resource
	private TestConfigVO testConfigVO;
	
	
	@GetMapping("authList")
	public Object list() {
		System.out.println("testConfigVO2 :" + testConfigVO);
		List<IAuther> lstAuther = autherRegistry.getAllAuther();
		for(IAuther auther : lstAuther){
			auther.auth();
		}
		return lstAuther.size();
	}
}
