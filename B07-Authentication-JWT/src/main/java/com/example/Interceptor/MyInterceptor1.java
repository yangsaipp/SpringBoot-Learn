package com.example.Interceptor;

import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

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
    	System.out.println("===preHandle");
    	String token = (String) request.getHeader("x-auth-token");	// 获取token
    	if(!StringUtils.isEmpty(token)) {
    		try {
    			// 检验并获取token中的认证信息
    			Claims claims = Jwts.parser().setSigningKey("ys_test").parseClaimsJws(token).getBody();
    			Date validDate = claims.get("validDate", Date.class);
    			Date expDate = claims.getExpiration();
    			System.out.println("validDate ====:" + validDate);
    			System.out.println("expDate ====:" + expDate);
    			if(validDate.compareTo(expDate) > 0) {
    				//refresh Token
    				Calendar now = Calendar.getInstance();
    				now.add(Calendar.MINUTE, 1);
    				Date exp = new Date(now.getTimeInMillis()); 	// 过期时间设置为1分钟
    				claims.setExpiration(exp);
    				String compactJws = Jwts.builder()
    						  .setClaims(claims)
    						  .signWith(SignatureAlgorithm.HS512, "ys_test")
    						  .compact();
    				response.addHeader("token", compactJws);
    			}
    			return true;
	    	} catch (SignatureException e) {
	    		response.setStatus(401);
	    		OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream(),"UTF-8");
	    		out.write("SignatureException.");
	    		out.flush();
	    		out.close();
	    		return false;
	    	} catch (ExpiredJwtException e) {
	    		response.setStatus(401);
	    		OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream(),"UTF-8");
	    		out.write("ExpiredJwtException.");
	    		out.flush();
	    		out.close();
	    		return false;
			}
    		
    	}else {
    		response.setStatus(401);
    		OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream(),"UTF-8");
    		out.write("无权访问.");
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
//        System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }

}