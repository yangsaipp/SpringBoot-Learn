package com.example.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.example.handler.TemplateVisitConfiguration.HandlerProperties;

/**
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年4月1日 杨赛
 */
public class TemplateVisitHandler {
	/** log */
	Logger log = LoggerFactory.getLogger(TemplateVisitHandler.class);

	/** handlerProperties */
	private HandlerProperties handlerProperties;
	
	/**
	 * 构造方法
	 * @param handlerProperties
	 */
	public TemplateVisitHandler() {
		super();
	}
	
	/**
	 * 构造方法
	 * @param handlerProperties
	 */
	public TemplateVisitHandler(HandlerProperties handlerProperties) {
		super();
		this.handlerProperties = handlerProperties;
	}
	
	/**
	 * 
	 * 根据request请求地址定位到对应视图。
	 * @param request request
	 * @param response response
	 * @return 对应视图
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String path = request.getContextPath();
		String basePath =request.getScheme()+"://"+request.getServerName()+":" +request.getServerPort() + path + "/";
		Map<String, Object> model = new HashMap<String, Object>();
        model.put("basePath", basePath);
        
		log.info("************* handleRequestPath:" + request.getServletPath());
        // 截取地址
        String reqPath = request.getServletPath();
        String templatePath = reqPath.substring(reqPath.indexOf("/") + 1, reqPath.lastIndexOf(handlerProperties.getVisitSuffix()));
        log.info("************* templatePath:" + templatePath);
        
		return new ModelAndView(templatePath, model);
	}
	

}
