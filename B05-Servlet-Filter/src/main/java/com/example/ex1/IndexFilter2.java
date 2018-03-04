package com.example.ex1;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IndexFilter2 implements Filter {
	Log log = LogFactory.getLog(IndexFilter2.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info(filterConfig.getInitParameter("url"));
		log.info("init IndexFilter2");
		System.out.println("222");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		String url = servletRequest.getServletContext().getRealPath("/");
//		System.out.println(url);
		HttpServletRequest objHttpServletRequest = (HttpServletRequest)servletRequest;
		System.out.println(servletRequest.getServletContext().getServerInfo());
		System.out.println(servletRequest.getServletContext().getContextPath());
		log.info("doFilter IndexFilter2");
		String strBackUrl = "http://" + objHttpServletRequest.getServerName() //服务器地址  
        + ":"   
        + objHttpServletRequest.getServerPort()           //端口号  
        + objHttpServletRequest.getContextPath()      //项目名称  
        + objHttpServletRequest.getServletPath()      //请求页面或其他地址  
        + "?" + (objHttpServletRequest.getQueryString()); //参数
		log.info(strBackUrl);
		
		StringBuilder sbBackUrl = new StringBuilder();
		sbBackUrl.append("http://").append(objHttpServletRequest.getServerName()) //服务器地址  
		.append(":").append(objHttpServletRequest.getServerPort()) //端口号  
		.append(objHttpServletRequest.getContextPath()) //项目名称  
		.append(objHttpServletRequest.getServletPath()); //请求页面或其他地址  
		if(objHttpServletRequest.getQueryString() != null) {
			sbBackUrl.append("?").append(objHttpServletRequest.getQueryString()); //参数
		}
		HttpServletResponse res = (HttpServletResponse)servletResponse;
		res.sendRedirect("https://www.baidu.com");;
		// filterChain.doFilter(servletRequest, servletResponse);
		log.info(sbBackUrl);

	}

	@Override
	public void destroy() {

	}
}