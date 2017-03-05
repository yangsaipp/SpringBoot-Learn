/******************************************************************************
* Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.comtop.exp.gateway.zuul;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;

/**
 * @author  杨赛
 * @since   jdk1.6
 * @version 2017年3月2日 杨赛
 */
public class MyRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
	
	
	Logger log = LoggerFactory.getLogger(MyRouteLocator.class);
	
	/**
	 * 构造函数
	 * @param servletPath
	 * @param properties
	 */
	public MyRouteLocator(String servletPath, ZuulProperties properties) {
		super(servletPath, properties);
		log.info(" ********************* my route locator init.");
	}

	@Override
	protected Map<String, ZuulRoute> locateRoutes() {
		log.info(" ********************* use my route locator.");
		Map<String, ZuulRoute> zunMap = super.locateRoutes();
//		for(Map.Entry<String, ZuulRoute> entry : zunMap.entrySet()) {
//			if(entry.getKey().equals("/baidu/**")) {
//				entry.getValue().setUrl("https://www.sogou.com/");
//			}
//		}
		return zunMap;
	}
	
	/**
	 * 
	 * @see org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator#refresh()
	 */
	@Override
	public void refresh() {
		log.info(" ********************* use my route refresh.");
//		Map<String, ZuulRoute> zunMap = super.locateRoutes();
//		for(Map.Entry<String, ZuulRoute> entry : zunMap.entrySet()) {
//			if(entry.getKey().equals("/baidu/**")) {
//				entry.getValue().setUrl("https://www.sogou.com/");
//			}
//		}
	}

}
