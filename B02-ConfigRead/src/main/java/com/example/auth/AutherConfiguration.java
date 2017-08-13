package com.example.auth;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutherConfiguration {
	
	@Autowired
	private List<IAuther> lstAuther;
	
	@Autowired
	private Map<String, IAuther> mapAuther;
	
	@Bean
	public AutherRegistry autherRegistry() {
		return new AutherRegistry(lstAuther);
	}
}
