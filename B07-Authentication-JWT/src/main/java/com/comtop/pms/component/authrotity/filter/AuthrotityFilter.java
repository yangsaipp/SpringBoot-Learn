/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市康拓普信息技术有限公司开发研制。未经本公司正式书面同意，其他任何个人、
 * 团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/

package com.comtop.pms.component.authrotity.filter;

import io.jsonwebtoken.Claims;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comtop.pms.component.authrotity.config.AuthrotityProperties;
import com.comtop.pms.component.authrotity.model.AccountInfo;
import com.comtop.pms.component.authrotity.utils.JwtUtil;
import com.comtop.pms.utils.SpringContextUtil;
import com.google.gson.Gson;

/**
 * 
 * 认证过滤器
 * @author lihuan
 *
 */
public class AuthrotityFilter implements Filter{
	
	private static AuthrotityProperties authrotityProperties;
	

	/**
	 * @return AuthrotityProperties
	 */
	public AuthrotityProperties getAuthrotityProperties(){
		if(null == authrotityProperties) {
			authrotityProperties = SpringContextUtil.getApplicationContext().getBean(AuthrotityProperties.class);
		}
		return authrotityProperties;
	}
	
	
	
	@Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  
            throws IOException, ServletException {  
	    HttpServletRequest httpRequest = (HttpServletRequest)request;  
	    HttpServletResponse httpResponse = (HttpServletResponse) response;
	    String requestPath = httpRequest.getRequestURI();
	    //读取配置文件里面的非过滤关键字,包含这些关键字的路径不进行过滤
	    if(null != getAuthrotityProperties().getExcludeKeywords() && requestPath.contains(getAuthrotityProperties().getExcludeKeywords())) {
	    	chain.doFilter(request, response);
	    	return;
	    }
		//获取authToken
    	String authToken = httpRequest.getHeader("token");
    	String url = httpRequest.getRequestURI();
    	System.out.println(url);
    	httpResponse.setCharacterEncoding("UTF-8");    
        httpResponse.setContentType("application/json; charset=utf-8");
        if (authToken != null) {
        	try {
				Claims claims = JwtUtil.parseJWT(authToken);
				String json = claims.getSubject();
	     		Gson gson = new Gson();
	     		AccountInfo accountInfo = gson.fromJson(json, AccountInfo.class);
	     		//判断当前账号是否存在
	     		//判断token是否过期
			} catch (Exception e) {
				httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        	httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid token");
	        	return;
			}
        }else {
             httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	 httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Missing or invalid Authorization header");
             return;  
        }
        chain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
