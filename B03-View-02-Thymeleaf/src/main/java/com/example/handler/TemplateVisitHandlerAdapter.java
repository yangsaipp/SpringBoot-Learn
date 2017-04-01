package com.example.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.LastModified;

/**
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年4月1日 杨赛
 */
public class TemplateVisitHandlerAdapter implements HandlerAdapter {
	
	/** log  */
	Logger log = LoggerFactory.getLogger(TemplateVisitHandlerAdapter.class);
	
	@Override
	public boolean supports(Object handler) {
		return (handler instanceof TemplateVisitHandler);
	}

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("************** MyHandlerAdapter ");
		return ((TemplateVisitHandler) handler).handleRequest(request, response);
	}

	@Override
	public long getLastModified(HttpServletRequest request, Object handler) {
		if (handler instanceof LastModified) {
			return ((LastModified) handler).getLastModified(request);
		}
		return -1L;
	}

}