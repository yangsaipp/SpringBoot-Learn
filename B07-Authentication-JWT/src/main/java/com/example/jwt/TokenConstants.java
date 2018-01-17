/******************************************************************************
* Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.example.jwt;

/**
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年7月11日 杨赛
 */
public class TokenConstants {
	
	/** cookie name */
	public static final String TOKEN_COOKIE_NAME = "access-token";
	
	/** response status 401 */
	public static final int TOKEN_UNAUTHORIZED_STATUS = 401;
	
	/** response status 403 */
	public static final int TOKEN_FORBIDDEN_STATUS = 403;

	/** response status 200 */
	public static final int TOKEN_OK_STATUS = 200;

	/** 有效时间  */
	public static final String TOKEN_VALID_DATE = "validDate";

	/** userId */
	public static final String TOKEN_USER_ID = "userIds";

	/** token刷新间隔  */
	public static final String TOKEN_REFRESH_INTERVAL = "refreshInterval";

	/** token刷新时间 */
	public static final String TOKEN_REFRESH_DATE = "refreshDate";
	
	/** employeeId */ 
	public static final String TOKEN_EMPLOYEE_ID = "employeeId";

	/** auccount */
	public static final String TOKEN_AUCCOUNT = "auccount";
}
