/******************************************************************************
* Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.example.jwt;

/**
 * JWT签名key的持有者.负责对外提供key
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年7月10日 杨赛
 */
public class SecretKeyHolder {

	
	/** key */
	private static String secretKey;
	
	/** key byte数组形式*/
	private static byte[] byteSecretKey;
	
	/**
	 * init
	 */
	public static void init() {
		byteSecretKey = javax.xml.bind.DatatypeConverter.parseBase64Binary("key");
		if(secretKey == null || "".equals(secretKey)) {
			throw new RuntimeException("secretKey未配置");
		}
	}
	
	/**
	 * 获取key 
	 * @return secretKey
	 */
	public static byte[] getSecretKey() {
		return byteSecretKey;
	}
}
