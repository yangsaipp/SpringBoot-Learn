/******************************************************************************
* Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.example.model;

/**
 * 负载均衡策略
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年3月20日 杨赛
 */
public enum LoadBalanceStrategy {

	/** 无负载策略 */
	NO,
	/**
	 * 轮询
	 */
	POLLING,
	/**
	 * 随机
	 */
	RANDOM
}
