package com.example.ex1;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IndexFilter implements Filter {
	Log log = LogFactory.getLog(IndexFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println(filterConfig.getInitParameter("url"));
		log.info("init IndexFilter");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		String url = servletRequest.getServletContext().getRealPath("/");
		System.out.println(url);
		System.out.println(servletRequest.getServletContext().getServerInfo());
		System.out.println(servletRequest.getServletContext().getContextPath());
		log.info("doFilter IndexFilter");
		filterChain.doFilter(servletRequest, servletResponse);

	}

	@Override
	public void destroy() {

	}
}