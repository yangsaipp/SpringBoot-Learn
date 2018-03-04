package com.example.ex2;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IndexFilter2 implements Filter {
	Log log = LogFactory.getLog(IndexFilter2.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info(filterConfig.getInitParameter("url"));
		log.info("init IndexFilter2");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		String url = servletRequest.getServletContext().getRealPath("/");
		System.out.println("啊" + url);
		System.out.println(servletRequest.getServletContext().getServerInfo());
		System.out.println(servletRequest.getServletContext().getContextPath());
		log.info("doFilter IndexFilter2");
		filterChain.doFilter(servletRequest, servletResponse);

	}

	@Override
	public void destroy() {

	}
}