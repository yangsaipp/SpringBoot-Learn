package com.example.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

/**
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年4月1日 杨赛
 */
public class TemplateVisitHandlerMapping extends AbstractUrlHandlerMapping {
	
	/** handler */
	private final TemplateVisitHandler handler;
	
	/**
	 * 构造函数
	 * @param handler handler
	 */
	public TemplateVisitHandlerMapping(TemplateVisitHandler handler) {
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
