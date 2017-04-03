package com.example.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.example.handler.TemplateVisitConfiguration.HandlerProperties;

/**
 * @author 杨赛
 * @since jdk1.7
 * @version 2017年4月1日 杨赛
 */
@EnableConfigurationProperties({ HandlerProperties.class })
public class TemplateVisitConfiguration {

	@Autowired
	protected HandlerProperties handlerProperties;
	
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
		return new TemplateVisitHandler(handlerProperties);
	}

	/**
	 * @param handler
	 *            handler
	 * @return TemplateVisitHandlerMapping
	 */
	@Bean
	public TemplateVisitHandlerMapping TemplateVisitHandlerMapping(
			TemplateVisitHandler handler) {
		TemplateVisitHandlerMapping mapping = new TemplateVisitHandlerMapping(
				handler, handlerProperties);
		return mapping;
	}
	
	@ConfigurationProperties("spring.template")
	class HandlerProperties {
		private String prefix;
		
		private String suffix = ".ht";
		
		public String getPrefix() {
			return prefix;
		}
		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}
		public String getSuffix() {
			return suffix;
		}
		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}
	}
}
