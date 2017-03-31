package com.example;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

@Component
public class MyViewResolver implements ViewResolver{

	Logger log = LoggerFactory.getLogger(MyViewResolver.class);
	
	@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		log.info("************** 自定义视图解析:viewName={}, locale={}", viewName, locale);
		return null;
	}

}
