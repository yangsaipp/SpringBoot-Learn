package com.example.auth.fourA;

import com.example.auth.IAuther;


public class FourAAuther implements IAuther {
	
	private FourAConfig fourAConfig;
	
	public FourAAuther(FourAConfig fourAConfig) {
		super();
		this.fourAConfig = fourAConfig;
	}

	@Override
	public Object auth() {
		System.out.println("fourAConfig:" + fourAConfig.toString());
		System.out.println("FourAAuther auth.");
		return null;
	}

	@Override
	public int order() {
		return fourAConfig.getOrder();
	}
	
}
