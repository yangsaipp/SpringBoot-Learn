/******************************************************************************
* Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.example.model;

/**
 * 路由服务发现方式
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年3月20日 杨赛
 */
public enum DiscoveryType {
	/** 使用Service Id的方式发现服务地址，目前主要是使用Eureka ServerId*/
	SERVICE_ID,
	
	/** 使用URL的方式发现服务地址 */
	URL;
}
