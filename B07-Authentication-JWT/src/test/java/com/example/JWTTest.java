/******************************************************************************
* Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.example;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

/**
 * @author  杨赛
 * @since   jdk1.7
 * @version 2017年7月5日 杨赛
 */
public class JWTTest {
	
	private String key = "test";

	@Test
	public void testCreate() {
		// We need a signing key, so we'll create one just for this example. Usually
		// the key would be read from your application configuration instead.
//		Key key = MacProvider.generateKey();
		
		Date iat = new Date();	// 签发时间
		Date nbf = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", "123456");
		String compactJws = Jwts.builder()
		  .setClaims(map)
		  .setSubject("Joe")
		  .setIssuedAt(iat)
		  .setNotBefore(nbf)
		  .setId(UUID.randomUUID().toString())
		  .signWith(SignatureAlgorithm.HS512, key)
		  .compact();
		System.out.println(compactJws);
		assert Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody().getSubject().equals("Joe");
		Claims claims  = Jwts.parser().setSigningKey("sss").parseClaimsJws(compactJws).getBody();
		claims.getIssuedAt().equals(iat);
		System.out.println(claims.getId());
		System.out.println(claims.get("userId"));
	}
}
