/******************************************************************************
* Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author  杨赛
 * @since   jdk1.7
 * @version 2018年2月6日 杨赛
 */
@Configuration
public class Test3Configuration {

	@Bean
	public TestConfigVO testConfigVO2() {
		TestConfigVO test = new TestConfigVO();
		System.out.println("Test 4 :" + test);
		return test;
	}
}
