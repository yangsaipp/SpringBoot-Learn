/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市康拓普信息技术有限公司开发研制。未经本公司正式书面同意，其他任何个人、
 * 团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/

package com.comtop.pms.component.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.comtop.pms.component.authrotity.filter.AuthrotityFilter;

/**
 * 初始化配置
 * @author lihuan
 *
 */
@Configuration
public class InitConfig {
	/** log */
    static Logger log = LoggerFactory.getLogger(InitConfig.class);
    
    /**
     * 
     */
    public InitConfig() {
    	log.error("---------------正在初始化配置---------------");
    }
    
    
    /**
     * 初始化鉴权过滤器
     * @return 过滤器
     */
    @Bean
    public FilterRegistrationBean interceptorRegistrationBean() {
    	final FilterRegistrationBean  registrationBean = new FilterRegistrationBean ();
        registrationBean.setFilter(new AuthrotityFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
