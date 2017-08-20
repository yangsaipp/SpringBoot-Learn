/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市康拓普信息技术有限公司开发研制。未经本公司正式书面同意，其他任何个人、
 * 团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/

package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
public class LogController {

	/**
	 * 日志
	 */
	static Logger log = LoggerFactory.getLogger(LogController.class);
	
	@RequestMapping(value = "/logTest", method = RequestMethod.GET)
	public Object logs(){
		System.out.println("sdfasdf");
		log.error("error");
		log.warn("warn");
		log.info("info");
		log.debug("debug");
		log.trace("trace");
		return null;
	}
	
}
