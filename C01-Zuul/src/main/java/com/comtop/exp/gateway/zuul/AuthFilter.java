package com.comtop.exp.gateway.zuul;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年4月5日 杨赛
 */
public class AuthFilter extends ZuulFilter {
	/**
	 * 日志
	 */
	static Logger log = LoggerFactory.getLogger(AuthFilter.class);

	@Override
	public boolean shouldFilter() {

		return true;
	}

	@Override
	public Object run() {

		final RequestContext ctx = RequestContext.getCurrentContext();
		final HttpServletRequest request = ctx.getRequest();
		// Here is the authorization header being read.
		final String xAuth = request.getHeader("Authorization");
		// Use the below method to add anything to the request header to read
		// downstream. if needed.
		log.info("****************** xAuth={}", xAuth);
		ctx.addZuulRequestHeader("abc", "abc");

		return null;
	}

	@Override
	public String filterType() {

		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}