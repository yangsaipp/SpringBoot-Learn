package com.example.handler;

import org.springframework.beans.BeansException;
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
		setOrder(-2);
	}

	
	/**
	 * Calls the {@link #registerHandlers} method in addition to the
	 * superclass's initialization.
	 */
	@Override
	public void initApplicationContext() throws BeansException {
		super.initApplicationContext();
		registerHandler("/**/*"+ handlerProperties.getVisitSuffix(), handler);
	}

}
