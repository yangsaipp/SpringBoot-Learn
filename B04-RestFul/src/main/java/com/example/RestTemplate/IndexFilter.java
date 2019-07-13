package com.example.RestTemplate;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		RequestUtil.setRequest((HttpServletRequest)servletRequest);
		RequestUtil.setResponse((HttpServletResponse)servletResponse);
		try { 
		filterChain.doFilter(servletRequest, servletResponse);
		} finally {
			RequestUtil.remove();
		}
	}

	@Override
	public void destroy() {

	}
}