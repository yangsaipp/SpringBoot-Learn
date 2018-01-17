/******************************************************************************
* Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.example;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年7月5日 杨赛
 */
public class JWTTest {
	
	private String key = "ys-secret";

	@Test
	public void testCreate() {
		// We need a signing key, so we'll create one just for this example. Usually
		// the key would be read from your application configuration instead.
//		Key key = MacProvider.generateKey();
		
		Date iat = new Date();	// 签发时间
		Date nbf = new Date();	// 定义在什么时间内是不可使用
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 1);
		Date exp = new Date(now.getTimeInMillis()); 	// 过期时间设置为1分钟
		now.add(Calendar.MINUTE, 5);
		Date validDate = new Date(now.getTimeInMillis()); 	// 有效刷新时间设置为5分钟
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("userId", user.getId());
		map.put("validDate", validDate);
		String compactJws = Jwts.builder()
		  .setClaims(map)
		  .setSubject("Joe")
		  .setIssuedAt(iat)
		  .setNotBefore(nbf)
		  .setExpiration(exp)
		  .setId(UUID.randomUUID().toString())
		  .signWith(SignatureAlgorithm.HS256, key)
		  .compact();
		System.out.println(compactJws);
		assert Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody().getSubject().equals("Joe");
		Claims claims  = Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody();
		claims.getIssuedAt().equals(iat);
		System.out.println(claims.getId());
		System.out.println(claims.getExpiration());
		System.out.println(claims.get("validDate", Date.class));
	}
	
	@Test
	public void testBase64Encode() {
		// We need a signing key, so we'll create one just for this example. Usually
		// the key would be read from your application configuration instead.
//		Key key = MacProvider.generateKey();
		
		Date iat = new Date();	// 签发时间
		Date nbf = new Date();	// 定义在什么时间内是不可使用
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 1);
		Date exp = new Date(now.getTimeInMillis()); 	// 过期时间设置为1分钟
		now.add(Calendar.MINUTE, 5);
		Date validDate = new Date(now.getTimeInMillis()); 	// 有效刷新时间设置为5分钟
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("userId", user.getId());
		map.put("validDate", validDate);
		String compactJws = Jwts.builder()
		  .setClaims(map)
		  .setSubject("杨赛")
		  .setIssuedAt(iat)
		  .setNotBefore(nbf)
		  .setExpiration(exp)
		  .setId(UUID.randomUUID().toString())
		  .signWith(SignatureAlgorithm.HS512, key)
		  .compact();
		System.out.println(compactJws);
		System.out.println(compactJws.split("\\.")[1]);
		byte[] arr = DatatypeConverter.parseBase64Binary(compactJws.split("\\.")[1]);
		String str = new String(arr);
		System.out.println(str);
	}
	
	@Test
	public void testCompress() {
		Date iat = new Date();	// 签发时间
		Date nbf = new Date();	// 定义在什么时间内是不可使用
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 1);
		Date exp = new Date(now.getTimeInMillis()); 	// 过期时间设置为1分钟
		now.add(Calendar.MINUTE, 5);
		Date validDate = new Date(now.getTimeInMillis()); 	// 有效刷新时间设置为5分钟
		Map<String, Object> map = new HashMap<String, Object>();
		String id = UUID.randomUUID().toString();
//		map.put("userId", user.getId());
		map.put("validDate", validDate);
		String compactJws = Jwts.builder()
		  .setClaims(map)
		  .setSubject("杨赛")
		  .setIssuedAt(iat)
		  .setNotBefore(nbf)
		  .setExpiration(exp)
		  .setId(id)
		  .signWith(SignatureAlgorithm.HS512, key)
		  .compact();
		System.out.println("压缩前：" + compactJws);
		compactJws = Jwts.builder()
				  .setClaims(map)
				  .setSubject("杨赛")
				  .compressWith(CompressionCodecs.GZIP)
				  .setIssuedAt(iat)
				  .setNotBefore(nbf)
				  .setExpiration(exp)
				  .setId(id)
				  .signWith(SignatureAlgorithm.HS512, key)
				  .compact();
		System.out.println("压缩后：" + compactJws);
	}
	
}
