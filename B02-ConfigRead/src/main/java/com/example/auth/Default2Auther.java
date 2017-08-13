package com.example.auth;

import org.springframework.stereotype.Component;

@Component
public class Default2Auther implements IAuther {

	@Override
	public Object auth() {
		System.out.println("DefaultAuther2");
		return null;
	}

	@Override
	public int order() {
		return 99;
	}

}
