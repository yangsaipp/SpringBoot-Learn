/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/

package com.example.jwt;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.FixedClock;
import io.jsonwebtoken.impl.TextCodec;

/**
 * token管理者，负责生成、验证、token
 * 
 * @author 杨赛
 * @since jdk1.7
 * @version 2017年7月10日 杨赛
 */
public class TokenManager {

	{
		SecretKeyHolder.init();
	}

	/**
	 * 构造函数
	 */
	private TokenManager() {
		//
	}

	/** log */
	private static final Logger log = LoggerFactory.getLogger(TokenManager.class);

	/** singletonInstance */
	private static TokenManager singletonInstance = new TokenManager();

	/**
	 * 获取单例
	 * 
	 * @return singletonInstance
	 */
	public static TokenManager getInstance() {
		return singletonInstance;
	}

	/**
	 * 根据tokenClaims创建token
	 * 
	 * @param tokenClaims
	 *            TokenClaims
	 * @return strToken
	 */
	public String createToken(TokenClaims tokenClaims) {
		String strToken = Jwts.builder().setClaims(tokenClaims.toClaims())
				.signWith(SignatureAlgorithm.HS512, SecretKeyHolder.getSecretKey()) // 使用key进行签名
				.compact();
		return strToken;
	}

	/**
	 * 使用指定时间验证并解析token，验证通过返回对对应对象，验证失败会抛出异常
	 * 
	 * @param token
	 *            token
	 * @param verifyDate
	 *            验证时间
	 * @return true 有效 false 无效
	 */
	public TokenClaims verifyAndParseToken(String token, Date verifyDate) {
		TokenClaims objTokenClaims = parseToken(token);
		return objTokenClaims;
	}

	/**
	 * 验证并刷新token
	 * 
	 * @param token
	 *            待验证和刷新的token
	 * @param verifyDate
	 *            验证时间
	 * @return 刷新后的token
	 * @throws VerifyException
	 *             验证失败将抛出认证异常
	 */
	public String verifyAndRefreshToken(String token, Date verifyDate) {
		TokenClaims objTokenClaims = verifyAndParseToken(token, verifyDate);
		return refreshToken(objTokenClaims, token);
	}

	/**
	 * 根据token里的间隔时间，刷新token的过期时间
	 * 
	 * @param token
	 *            旧token
	 * @return newToken 若过期时间等于有效时间则返回旧token
	 * @throws VerifyException
	 *             验证失败将抛出认证异常
	 */
	public String refreshToken(String token) {
		return refreshToken(token, new Date());
	}

	/**
	 * 根据token里的间隔时间，刷新token的过期时间
	 * 
	 * @param token
	 *            旧token
	 * @param verifyDate
	 *            验证时间
	 * @return newToken 若过期时间等于有效时间则返回旧token
	 * @throws VerifyException
	 *             验证失败将抛出认证异常
	 */
	public String refreshToken(String token, Date verifyDate) {
		TokenClaims objClaims = parseToken(token, verifyDate);
		return refreshToken(objClaims, token);

	}

	/**
	 * 根据token里的间隔时间，刷新token的过期时间
	 * 
	 * @param objClaims
	 *            objClaims
	 * @param oldToken
	 *            旧token
	 * @return newToken 若过期时间等于有效时间则返回旧token
	 */
	public String refreshToken(TokenClaims objClaims, String oldToken) {
		Date dateNewExp = getRefreshExpDate(objClaims);
		// 刷新后的过期时间与原来过期时间相同
		if (objClaims.getExpiration().equals(dateNewExp)) {
			return oldToken;
		}

		objClaims.setExpiration(dateNewExp);
		objClaims.setRefreshDate(new Date());
		String strNewToken = Jwts.builder().setClaims(objClaims.toClaims())
				.signWith(SignatureAlgorithm.HS512, SecretKeyHolder.getSecretKey()).compact();
		return strNewToken;
	}

	/**
	 * 获取刷新后的过期时间
	 * 
	 * @param claims
	 *            TokenClaims
	 * @return 新的过期时间 若过期时间等于有效时间则返回null
	 */
	private Date getRefreshExpDate(TokenClaims claims) {
		Date dateValidDate = claims.getValidDate();

		Date dateExp = claims.getExpiration();
		if (dateExp.equals(dateValidDate)) {
			return dateExp;
		}

		int iRefreshInterval = claims.getRefreshInterval();
		Calendar objNow = Calendar.getInstance();
		objNow.add(Calendar.MINUTE, iRefreshInterval);
		Date dateRefreshExp = new Date(objNow.getTimeInMillis());
		if (dateValidDate != null && dateRefreshExp.after(dateValidDate)) {
			return dateValidDate;
		}
		return dateRefreshExp;
	}

	/**
	 * 按照系统当前时间使用JWT三方开源包对token进行解析校验，解析校验通过将会返回
	 * TokenClaims对象，若不通过将会抛出VerifyException。
	 * 
	 * @param token
	 *            token
	 * @throws VerifyException
	 *             验证失败将抛出认证异常
	 * @return claims
	 */
	public TokenClaims parseToken(String token) {
		return parseToken(token, new Date());
	}

	/**
	 * 按照给定的验证时间使用JWT三方开源包对token进行解析校验，解析校验通过将会返回
	 * TokenClaims对象，若不通过将会抛出VerifyException。
	 * 
	 * @param token
	 *            token
	 * @param verifyDate
	 *            verifyDate
	 * @throws VerifyException
	 *             验证失败将抛出认证异常
	 * @return Claims
	 */
	private TokenClaims parseToken(String token, Date verifyDate) {

		Claims objClaims = Jwts.parser().setSigningKey(SecretKeyHolder.getSecretKey())
				.setClock(new FixedClock(verifyDate)).parseClaimsJws(token).getBody();
		return new TokenClaims(objClaims);

	}

	/**
	 * 不验证签名获取token中Claims
	 * 
	 * @param token
	 *            token
	 * @return Claims
	 */
	public TokenClaims parseTokenWithoutSignature(String token) {

		if (token == null) {
			return null;
		}
		String strTokenClaims = token.split("\\.")[1];
		String strCalimsJson = TextCodec.BASE64URL.decodeToString(strTokenClaims);
		try {
			ObjectMapper objMapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			Map<String, Object> objCalims = objMapper.readValue(strCalimsJson, Map.class);
			return new TokenClaims(objCalims);
		} catch (IOException e) {
			log.error("解析token发生错误。calimsJsonStr={}", strCalimsJson, e);
			return null;
		}
	}
}
