package com.example;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;

public class IndexFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	Random r = new Random(10);

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
//		String url = servletRequest.getServletContext().getRealPath("/");
		String TRACE_ID  = r.nextInt() + "";
		System.out.println(TRACE_ID);
		MDC.put("TRACE_ID", TRACE_ID);
		filterChain.doFilter(servletRequest, servletResponse);
		MDC.clear();
	}

	@Override
	public void destroy() {

	}
}