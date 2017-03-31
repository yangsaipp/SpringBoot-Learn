package com.example.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

public class MyHandler {
	Logger log = LoggerFactory.getLogger(MyHandler.class);

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("************* handleRequest ...........");
		return new ModelAndView("test/list");
	}

}
