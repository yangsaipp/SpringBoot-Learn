package com.example.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

public class MyHandlerMapping extends AbstractUrlHandlerMapping {

	private final MyHandler handler;
	
	public MyHandlerMapping(MyHandler handler) {
		super();
		this.handler = handler;
		setOrder(-200);
	}

	
	@Override
	protected Object lookupHandler(String urlPath, HttpServletRequest request) throws Exception {
		registerHandler("/**/*.th", handler);
		return super.lookupHandler(urlPath, request);
	}
}
