package com.example.auth;

import org.springframework.stereotype.Component;

@Component
public class DefaultAuther implements IAuther {

	@Override
	public Object auth() {
		System.out.println("DefaultAuther");
		return null;
	}

	@Override
	public int order() {
		return Integer.MAX_VALUE;
	}

}
