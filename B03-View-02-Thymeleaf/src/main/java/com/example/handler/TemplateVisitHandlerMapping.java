package com.example.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

import com.example.handler.TemplateVisitConfiguration.HandlerProperties;

/**
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年4月1日 杨赛
 */
public class TemplateVisitHandlerMapping extends AbstractUrlHandlerMapping {
	
	/** handler */
	private final TemplateVisitHandler handler;
	
	/** handlerProperties */
	private final HandlerProperties handlerProperties;
	/**
	 * 构造函数
	 * @param handler handler
	 * @param handlerProperties handlerProperties
	 */
	public TemplateVisitHandlerMapping(TemplateVisitHandler handler, HandlerProperties handlerProperties) {
		super();
		this.handlerProperties = handlerProperties;
		this.handler = handler;
		setOrder(-200);
	}

	
	@Override
	protected Object lookupHandler(String urlPath, HttpServletRequest request) throws Exception {
		registerHandler("/**/*"+ handlerProperties.getVisitSuffix(), handler);
		return super.lookupHandler(urlPath, request);
	}
}
