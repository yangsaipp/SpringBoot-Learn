package com.comtop.pms.component.authrotity.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.comtop.pms.component.authrotity.config.AuthrotityConstant;
import com.google.gson.Gson;

/**
 * @author lihuan
 *
 */
@Component
public class JwtUtil {
	
    private static String profiles = "test";
	
	/**
	 * 由字符串生成加密key
	 * 
	 * @return 加密后的key值
	 */
	public static SecretKey generalKey(){
		String stringKey = profiles+AuthrotityConstant.JWT_SECRET;
		byte[] encodedKey = Base64.decodeBase64(stringKey);
	    SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
	    return key;
	}

	/**
	 * 创建jwtToken
	 * @param id
	 * @param subject
	 * @param ttlMillis
	 * @return Claims
	 * @throws Exception
	 */
	public static String createJWT(String id, String subject, long ttlMillis) throws Exception {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		//设置token生效时间,即时生效
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey key = generalKey();
		JwtBuilder builder = Jwts.builder()
			.setId(id)
			.setIssuedAt(now)
			.setSubject(subject)
		    .signWith(signatureAlgorithm, key);
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		return builder.compact();
	}
	
	/**
	 * 解密token
	 * @param token
	 * @return Claims
	 * @throws Exception
	 */
	public static Claims parseJWT(String token) throws Exception{
		SecretKey key = generalKey();
		Claims claims = Jwts.parser()         
		   .setSigningKey(key)
		   .parseClaimsJws(token).getBody();
		return claims;
	}
	
	/**
	 * 生成subject信息
	 * @param obj
	 * @return json字符串
	 */
	public static String generalSubject(Object obj){
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
}
