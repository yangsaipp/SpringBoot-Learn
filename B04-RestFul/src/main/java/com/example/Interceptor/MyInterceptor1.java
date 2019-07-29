package com.example.Interceptor;

import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器1
 *
 * @author   单红宇(365384722)
 * @myblog  http://blog.csdn.net/catoop/
 * @create    2016年1月7日
 */

public class MyInterceptor1 implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	HandlerMethod method = (HandlerMethod)handler;
    	System.out.println(method.getMethod());
    	System.out.println(method.getBeanType());
    	String token = (String) request.getAttribute("x-auth-token");
    	if(!StringUtils.isEmpty(token) && request.getSession().getAttribute(token) != null) {
    		return true;
    	}else {
    		response.setStatus(401);
    		OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream(),"UTF-8");
    		out.write("验证错误.");
    		out.flush();
    		out.close();
    		return false;
    	}
    	// 只有返回true才会继续向下执行，返回false取消当前请求
    	
//    	String username = (String) request.getAttribute("username");
//    	String password = (String) request.getAttribute("password");
//    	
//    	User u = User.get(username, password);
//    	if(u != null) {
//    		Cookie cookie = new Cookie("x-auth-token", request.getSession().getId());
//    		request.getSession().setAttribute("user", u);
//    		response.addCookie(cookie);
//    		return true;
//    	}else {
//    		
//    	}
    	
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }

}