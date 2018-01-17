/******************************************************************************
* Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.example.jwt;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

/**
 * token对象
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年7月14日 杨赛
 */
public class TokenClaims {
	
	/** Claims */
	private Claims claims;
	
	/** 有效时间  */
	public static final String VALID_DATE = "validDate";

	/** token刷新间隔  */
	public static final String REFRESH_INTERVAL = "refreshInterval";

	/** token刷新时间 */
	public static final String REFRESH_DATE = "refreshDate";
	
	/** userId */
	public static final String USER_ID = "userId";
	
	/** employeeId */ 
	public static final String EMPLOYEE_ID = "employeeId";

	/** EMPLOYEE_NAME */
	public static final String EMPLOYEE_NAME = "employeeName";

	/** account */
	public static final String ACCOUNT = "account";
	
	/** ORG_ID */
	public static final String ORG_ID = "orgId";
	
	/** ORG_CODE */
	public static final String ORG_CODE = "orgCode";
	
	/** ORG_CODE */
	public static final String ORG_NAME = "orgName";

	/** manyIdentity */
//	public static final String MANYID_ENTITY = "manyIdentity";

	/** manyIdentity */
	public static final String FOUR_A_TICKET = "4ATicket";
	
	/**
	 * 构造函数
	 */
	public TokenClaims() {
		claims = new DefaultClaims();
//		setManyIdentity(false);
	}
	
	/**
	 * 
	 * 构造函数
	 * @param map map
	 */
	public TokenClaims(Map<String, Object> map) {
		claims = new DefaultClaims(map);
	}
	
	/**
	 * 
	 * 构造函数
	 * @param claims claims
	 */
	public TokenClaims(Claims claims) {
		this.claims = claims;
	}
	
	/**
	 * 
	 * toClaims 
	 * @return Claims
	 */
	public Claims toClaims() {
		return claims;
	}
	
	/**
	 * @return 获取 有效时间(validDate)
	 */
	public Date getValidDate() {
		return claims.get(VALID_DATE, Date.class);
	}

	/**
	 * @param validDate 设置  有效时间(validDate)
	 * @return TokenClaims
	 */
	public TokenClaims setValidDate(Date validDate) {
		claims.put(VALID_DATE, validDate);
		return this;
	}

	/**
	 * @return 获取 刷新时间(refreshDate)
	 */
	public Date getRefreshDate() {
		return claims.get(REFRESH_DATE, Date.class);
	}

	/**
	 * @param refreshDate 设置 刷新时间(refreshDate)
	 * @return TokenClaims
	 */
	public TokenClaims setRefreshDate(Date refreshDate) {
		claims.put(REFRESH_DATE, refreshDate);
		return this;
	}

	/**
	 * @return 获取 刷新间隔时间(refreshInterval),以分钟为单位
	 */
	public int getRefreshInterval() {
		return claims.get(REFRESH_INTERVAL, Integer.class);
	}

	/**
	 * @param refreshInterval 设置 刷新间隔时间(refreshInterval),以分钟为单位
	 * @return TokenClaims
	 */
	public TokenClaims setRefreshInterval(int refreshInterval) {
		claims.put(REFRESH_INTERVAL, refreshInterval);
		return this;
	}

	/**
	 * @return 获取 employeeId
	 */
	public String getEmployeeId() {
		return claims.get(EMPLOYEE_ID, String.class);
	}

	/**
	 * @param employeeId 设置 employeeId
	 * @return TokenClaims
	 */
	public TokenClaims setEmployeeId(String employeeId) {
		claims.put(EMPLOYEE_ID, employeeId);
		return this;
	}

	/**
	 * @return 获取 employeeName
	 */
	public String getEmployeeName() {
		return claims.get(EMPLOYEE_NAME, String.class);
	}
	
	/**
	 * @param employeeName 设置 employeeName
	 * @return TokenClaims
	 */
	public TokenClaims setEmployeeName(String employeeName) {
		claims.put(EMPLOYEE_NAME, employeeName);
		return this;
	}

	/**
	 * 
	 * @see io.jsonwebtoken.Claims#getIssuer()
	 * @return Issuer 签发者
	 */
	public String getIssuer() {
		return claims.getIssuer();
	}

	/**
	 * 
	 * @param iss 签发者
	 * @see io.jsonwebtoken.Claims#setIssuer(java.lang.String)
	 * @return TokenClaims
	 */
	public TokenClaims setIssuer(String iss) {
		claims.setIssuer(iss);
		return this;
	}

	/**
	 * 
	 * @see io.jsonwebtoken.Claims#getSubject()
	 * @return Subject 所面向的用户
	 */
	public String getSubject() {
		return claims.getSubject();
	}

	/**
	 * 
	 * @param sub 所面向的用户
	 * @see io.jsonwebtoken.Claims#setSubject(java.lang.String)
	 * @return TokenClaims
	 */
	public TokenClaims setSubject(String sub) {
		claims.setSubject(sub);
		return this;
	}

	/**
	 * 
	 * @see io.jsonwebtoken.Claims#getAudience()
	 * @return Audience
	 */
	public String getAudience() {
		return claims.getAudience();
	}

	/**
	 * 
	 * @param aud 接收方
	 * @return TokenClaims
	 * @see io.jsonwebtoken.Claims#setAudience(java.lang.String)
	 */
	public TokenClaims setAudience(String aud) {
		 claims.setAudience(aud);
		return this;
	}

	/**
	 * 
	 * @return 过期时间
	 * @see io.jsonwebtoken.Claims#getExpiration()
	 */
	public Date getExpiration() {
		return claims.getExpiration();
	}

	/**
	 * 
	 * @param exp 过期时间
	 * @return TokenClaims
	 * @see io.jsonwebtoken.Claims#setExpiration(java.util.Date)
	 */
	public TokenClaims setExpiration(Date exp) {
		claims.setExpiration(exp);
		return this;
	}

	/**
	 * 
	 * @return 不可用时间，在此之前该JWT都是不可用的
	 * @see io.jsonwebtoken.Claims#getNotBefore()
	 */
	public Date getNotBefore() {
		return claims.getNotBefore();
	}

	/**
	 * 
	 * @param nbf 不可用时间，在此之前该JWT都是不可用的
	 * @return TokenClaims
	 * @see io.jsonwebtoken.Claims#setNotBefore(java.util.Date)
	 */
	public TokenClaims setNotBefore(Date nbf) {
		claims.setNotBefore(nbf);
		return this;
	}

	/**
	 * 
	 * @return 签发时间
	 * @see io.jsonwebtoken.Claims#getIssuedAt()
	 */
	public Date getIssuedAt() {
		return claims.getIssuedAt();
	}

	/**
	 * 
	 * @param iat 签发时间
	 * @return TokenClaims
	 * @see io.jsonwebtoken.Claims#setIssuedAt(java.util.Date)
	 */
	public TokenClaims setIssuedAt(Date iat) {
		claims.setIssuedAt(iat);
		return this;
	}

	/**
	 * 
	 * @return id
	 * @see io.jsonwebtoken.Claims#getId()
	 */
	public String getId() {
		return claims.getId();
	}

	/**
	 * 
	 * @param jti id
	 * @return TokenClaims
	 * @see io.jsonwebtoken.Claims#setId(java.lang.String)
	 */
	public TokenClaims setId(String jti) {
		claims.setId(jti);
		return this;
	}
	
//	/**
//	 * 设置是否有多身份
//	 * @param manyIdentity true 多身份 false单一身份
//	 * @return TokenClaims
//	 */
//	public TokenClaims setManyIdentity(boolean manyIdentity) {
//		claims.put(MANYID_ENTITY, manyIdentity);
//		return this;
//	}
//	
//	/**
//	 * 
//	 * @return true 多身份 false单一身份
//	 */
//	public boolean isManyIdentity() {
//		return claims.get(MANYID_ENTITY, Boolean.class);
//	}
	
	/**
	 * set account
	 * @param account account
	 * @return TokenClaims
	 */
	public TokenClaims setAccount(String account) {
		claims.put(ACCOUNT, account);
		return this;
	}
	
	/** 
	 * @return ACCOUNT
	 */
	public String getAccount() {
		return claims.get(ACCOUNT, String.class);
	}
	
	/**
	 * set userId
	 * @param userId userId
	 * @return TokenClaims
	 */
	public TokenClaims setUserId(String userId) {
		claims.put(USER_ID, userId);
		return this;
	}
	
	/** 
	 * @return userId
	 */
	public String getUserId() {
		return claims.get(USER_ID, String.class);
	}
	
	/**
	 * set orgId
	 * @param orgId orgId
	 * @return TokenClaims
	 */
	public TokenClaims setOrgId(String orgId) {
		claims.put(ORG_ID, orgId);
		return this;
	}
	
	/** 
	 * @return orgId
	 */
	public String getOrgId() {
		return claims.get(ORG_ID, String.class);
	}
	
	/**
	 * set orgCode
	 * @param orgCode orgCode
	 * @return TokenClaims
	 */
	public TokenClaims setOrgCode(String orgCode) {
		claims.put(ORG_CODE, orgCode);
		return this;
	}
	
	/** 
	 * @return orgCode
	 */
	public String getOrgCode() {
		return claims.get(ORG_CODE, String.class);
	}
	
	/**
	 * set orgName
	 * @param orgName orgName
	 * @return TokenClaims
	 */
	public TokenClaims setOrgName(String orgName) {
		claims.put(ORG_NAME, orgName);
		return this;
	}
	
	/** 
	 * @return orgName
	 */
	public String getOrgName() {
		return claims.get(ORG_NAME, String.class);
	}
	
	/**
	 * set4ATicket
	 * @param ticket 4ATicket
	 * @return TokenClaims
	 */
	public TokenClaims set4ATicket(String ticket) {
		claims.put(FOUR_A_TICKET, ticket);
		return this;
	}
	
	/** 
	 * @return 4ATicket
	 */
	public String get4ATicket() {
		return claims.get(FOUR_A_TICKET, String.class);
	}
}
