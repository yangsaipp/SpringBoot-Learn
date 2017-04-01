package com.example.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年4月1日 杨赛
 */
@Component
public class TemplateVisitConfiguration {

	/**
	 * @return TemplateVisitHandlerAdapter
	 */
	@Bean
	public TemplateVisitHandlerAdapter TemplateVisitHandlerAdapter() {
	return new TemplateVisitHandlerAdapter();
	}

	/**
	 * @return TemplateVisitHandler
	 */
	@Bean
	public TemplateVisitHandler TemplateVisitHandler() {
	return new TemplateVisitHandler();
	}

	/**
	 * @param handler handler
	 * @return TemplateVisitHandlerMapping
	 */
	@Bean
	public TemplateVisitHandlerMapping TemplateVisitHandlerMapping(TemplateVisitHandler handler) {
	TemplateVisitHandlerMapping mapping = new TemplateVisitHandlerMapping(handler);
	return mapping;
	}
}
