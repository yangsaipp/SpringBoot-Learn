/******************************************************************************
* Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.example.ex1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 杨赛
 * @since jdk1.7
 * @version 2017年8月18日 杨赛
 */
@Configuration
public class WebConfiguration {

	@Bean
	public FilterRegistrationBean indexFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new IndexFilter());
		registration.addUrlPatterns("/");
		Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("url", "http://baidu.com");
		registration.setInitParameters(initParameters);
		return registration;
	}
}
