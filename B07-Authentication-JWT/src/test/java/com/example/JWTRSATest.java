/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/

package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author 杨赛
 * @since jdk1.7
 * @version 2017年7月5日 杨赛
 */
public class JWTRSATest {
	public static final String KEY_ALGORITHM = "RSA";
	
	@Test
	public void testRSA() throws NoSuchAlgorithmException {

		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		// 通过对象 KeyPairGenerator 获取对象KeyPair
		KeyPair keyPair = keyPairGen.generateKeyPair();

		// 通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		
		
		Date iat = new Date(); // 签发时间
		Date nbf = new Date(); // 定义在什么时间内是不可使用
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 1);
		Date exp = new Date(now.getTimeInMillis()); // 过期时间设置为1分钟
		now.add(Calendar.MINUTE, 5);
		Date validDate = new Date(now.getTimeInMillis()); // 有效刷新时间设置为5分钟
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("userId", user.getId());
		map.put("validDate", validDate);
		//使用私钥签名
		String compactJws = Jwts.builder().setClaims(map).setSubject("Joe")
				.setIssuedAt(iat).setNotBefore(nbf).setExpiration(exp)
				.setId(UUID.randomUUID().toString())
				.signWith(SignatureAlgorithm.RS256, privateKey).compact();
		System.out.println(compactJws);
		// 使用公钥验证
		Claims claims = Jwts.parser().setSigningKey(publicKey)
				.parseClaimsJws(compactJws).getBody();
		claims.getIssuedAt().equals(iat);
		System.out.println(claims.getId());
		System.out.println(claims.getSubject());
		Assert.assertEquals("Subject不符合", "Joe", claims.getSubject());
		System.out.println(claims.getExpiration());
		System.out.println(claims.get("validDate", Date.class));
	}

	//解码返回byte
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    //编码返回字符串
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
    
    /** 
     * 从字符串中加载公钥 
     *  
     * @param publicKeyStr 
     *            公钥数据字符串 
     * @throws Exception 
     *             加载公钥时产生的异常 
     */  
    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)  
            throws Exception {  
        try {  
            byte[] buffer = decryptBASE64(publicKeyStr);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);  
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e) {  
            throw new Exception("公钥非法");  
        } catch (NullPointerException e) {  
            throw new Exception("公钥数据为空");  
        }  
    }  
	
    /** 
     * 从字符串中加载私钥 
     *  
     * @param privateKeyStr 
     *            私钥数据字符串 
     * @throws Exception 
     *             加载私钥时产生的异常 
     */  
    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)  
            throws Exception {  
        try {  
            byte[] buffer = decryptBASE64(privateKeyStr);  
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e) {  
            throw new Exception("私钥非法");  
        } catch (NullPointerException e) {  
            throw new Exception("私钥数据为空");  
        }  
    }  
    
	@Test
	public void testRSA2() throws Exception {
		
//		Map<String, Object> keyMap = CreateSecrteKey.initKey();
//        String strPublicKey = CreateSecrteKey.getPublicKey(keyMap);
//        System.out.println(strPublicKey);
//        String strPrivateKey = CreateSecrteKey.getPrivateKey(keyMap);
//        System.out.println(strPrivateKey);
		
		String strPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEbcgn38EU1WD4uPi897VHIf/xKHy17+uN5xtQ"+"\r\n"
				+ "yuUadoHCPPe5fE3rqpXIqjYm+970hBKhlIWCOJnJhaKypm3QnEId16gGZyH/4LXaV+gspFl1du0g"+"\r\n"
				+ "OkljrWpLZ9yxo27SPotUMXi5AoHepyYVqnanoa4sH0SNKITC+jV3n22wywIDAQAB";
		
		String strPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMRtyCffwRTVYPi4+Lz3tUch//Eo"+"\r\n"
				+ "fLXv643nG1DK5Rp2gcI897l8TeuqlciqNib73vSEEqGUhYI4mcmForKmbdCcQh3XqAZnIf/gtdpX"+"\r\n"
				+ "6CykWXV27SA6SWOtaktn3LGjbtI+i1QxeLkCgd6nJhWqdqehriwfRI0ohML6NXefbbDLAgMBAAEC"+"\r\n"
				+ "gYArZM1I8cdn4NOxXrukk1x6wGoYToducfgpuxpvuMLwG6oCzRE06t8T5vV8BQ49gnr4rYyoIXV1"+"\r\n"
				+ "FSf0Ow98RAQSJGeNZL5rfFeme885tcaN4CL8j00Nf7pprUaJWT8tvVLMl3cUgoKfpP5UdYewYdYK"+"\r\n"
				+ "GYxANawLrXmj/ANAvwv0UQJBAPOhjmudP3x6tRYU9Kdv6S4VVh2ocXvtoEWjGNsnXUunalL4glrr"+"\r\n"
				+ "qhO+Nx+JSDe8O515TRk2B0kf5aJstOXUHrUCQQDOZr5hVIHEOmRMa2Zlh17AMm2aqcYtFa6wBeMF"+"\r\n"
				+ "E/tAiQZ82skuYAmHXEJ/TKJG8KvkIAvLvKqx7Y5YilRrXMF/AkADtcQbc2uSE7RKP8tx+UxNvVlw"+"\r\n"
				+ "Xi5MOtQj+1XXkIPlMQl3df1WMRyH7G+edVK6No2Z2k0IQ0BdJrUyggFHNrG5AkAVdouvlVkVLblG"+"\r\n"
				+ "VRjF0AjjVZseLS9les66kTIlUgmbkWQyUZIAF46GG+ryT+bEF7zYMRjo68qYTAq0XFYg//lDAkBU"+"\r\n"
				+ "A5432FdbyUw7E1wXpwArn8ZlDXMHO+WAeCUNX54tvwyLK1xE6GXWeKhVFFlELoOmy2yyuS0thbOS"+"\r\n"
				+ "dlVOJZXN";
		
		// 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = loadPublicKeyByStr(strPublicKey);
        RSAPrivateKey privateKey = loadPrivateKeyByStr(strPrivateKey);
        
        
		Date iat = new Date(); // 签发时间
		Date nbf = new Date(); // 定义在什么时间内是不可使用
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 1);
		Date exp = new Date(now.getTimeInMillis()); // 过期时间设置为1分钟
		now.add(Calendar.MINUTE, 5);
		Date validDate = new Date(now.getTimeInMillis()); // 有效刷新时间设置为5分钟
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("userId", user.getId());
		map.put("validDate", validDate);
		//使用私钥签名
		String compactJws = Jwts.builder().setClaims(map).setSubject("Joe")
				.setIssuedAt(iat).setNotBefore(nbf).setExpiration(exp)
				.setId(UUID.randomUUID().toString())
				.signWith(SignatureAlgorithm.RS256, privateKey).compact();
		System.out.println(compactJws);
		// 使用公钥验证
		Claims claims = Jwts.parser().setSigningKey(publicKey)
				.parseClaimsJws(compactJws).getBody();
		claims.getIssuedAt().equals(iat);
		Assert.assertEquals("Subject不符合", "Joe", claims.getSubject());
		System.out.println(claims.getId());
		System.out.println(claims.getExpiration());
		System.out.println(claims.get("validDate", Date.class));
	}
	
	/**
	 * http://bobao.360.cn/news/detail/1377.html.安全漏洞：使用公钥伪造能通过验证的token。
	 * jsonwebtoken目前没发现该漏洞。
	 * 因为验证时若token指定的是HS256算法key只能是string或者byte[],若是RSA则只能是publickey的实例对象。
	 * 但是一定要避免使用公钥（string）来验证token的写法。
	 * @throws Exception
	 */
	@Test
	public void testRSA3() throws Exception {
		
//		Map<String, Object> keyMap = CreateSecrteKey.initKey();
//        String strPublicKey = CreateSecrteKey.getPublicKey(keyMap);
//        System.out.println(strPublicKey);
//        String strPrivateKey = CreateSecrteKey.getPrivateKey(keyMap);
//        System.out.println(strPrivateKey);
		
		String strPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEbcgn38EU1WD4uPi897VHIf/xKHy17+uN5xtQ"+"\r\n"
				+ "yuUadoHCPPe5fE3rqpXIqjYm+970hBKhlIWCOJnJhaKypm3QnEId16gGZyH/4LXaV+gspFl1du0g"+"\r\n"
				+ "OkljrWpLZ9yxo27SPotUMXi5AoHepyYVqnanoa4sH0SNKITC+jV3n22wywIDAQAB";
		
		String strPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMRtyCffwRTVYPi4+Lz3tUch//Eo"+"\r\n"
				+ "fLXv643nG1DK5Rp2gcI897l8TeuqlciqNib73vSEEqGUhYI4mcmForKmbdCcQh3XqAZnIf/gtdpX"+"\r\n"
				+ "6CykWXV27SA6SWOtaktn3LGjbtI+i1QxeLkCgd6nJhWqdqehriwfRI0ohML6NXefbbDLAgMBAAEC"+"\r\n"
				+ "gYArZM1I8cdn4NOxXrukk1x6wGoYToducfgpuxpvuMLwG6oCzRE06t8T5vV8BQ49gnr4rYyoIXV1"+"\r\n"
				+ "FSf0Ow98RAQSJGeNZL5rfFeme885tcaN4CL8j00Nf7pprUaJWT8tvVLMl3cUgoKfpP5UdYewYdYK"+"\r\n"
				+ "GYxANawLrXmj/ANAvwv0UQJBAPOhjmudP3x6tRYU9Kdv6S4VVh2ocXvtoEWjGNsnXUunalL4glrr"+"\r\n"
				+ "qhO+Nx+JSDe8O515TRk2B0kf5aJstOXUHrUCQQDOZr5hVIHEOmRMa2Zlh17AMm2aqcYtFa6wBeMF"+"\r\n"
				+ "E/tAiQZ82skuYAmHXEJ/TKJG8KvkIAvLvKqx7Y5YilRrXMF/AkADtcQbc2uSE7RKP8tx+UxNvVlw"+"\r\n"
				+ "Xi5MOtQj+1XXkIPlMQl3df1WMRyH7G+edVK6No2Z2k0IQ0BdJrUyggFHNrG5AkAVdouvlVkVLblG"+"\r\n"
				+ "VRjF0AjjVZseLS9les66kTIlUgmbkWQyUZIAF46GG+ryT+bEF7zYMRjo68qYTAq0XFYg//lDAkBU"+"\r\n"
				+ "A5432FdbyUw7E1wXpwArn8ZlDXMHO+WAeCUNX54tvwyLK1xE6GXWeKhVFFlELoOmy2yyuS0thbOS"+"\r\n"
				+ "dlVOJZXN";
		
		// 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = loadPublicKeyByStr(strPublicKey);
        RSAPrivateKey privateKey = loadPrivateKeyByStr(strPrivateKey);
        
        
		Date iat = new Date(); // 签发时间
		Date nbf = new Date(); // 定义在什么时间内是不可使用
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 1);
		Date exp = new Date(now.getTimeInMillis()); // 过期时间设置为1分钟
		now.add(Calendar.MINUTE, 5);
		Date validDate = new Date(now.getTimeInMillis()); // 有效刷新时间设置为5分钟
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("userId", user.getId());
		map.put("validDate", validDate);
		//使用私钥签名
		String compactJws = Jwts.builder().setClaims(map).setSubject("Joe")
				.setIssuedAt(iat).setNotBefore(nbf).setExpiration(exp)
				.setId(UUID.randomUUID().toString())
				.signWith(SignatureAlgorithm.HS256, strPublicKey).compact();
		System.out.println(compactJws);
		// 使用公钥验证
		Claims claims = Jwts.parser().setSigningKey(publicKey)
				.parseClaimsJws(compactJws).getBody();
		claims.getIssuedAt().equals(iat);
		Assert.assertEquals("Subject不符合", "Joe", claims.getSubject());
		System.out.println(claims.getId());
		System.out.println(claims.getExpiration());
		System.out.println(claims.get("validDate", Date.class));
	}

	/**
	 * http://bobao.360.cn/news/detail/1377.html.安全漏洞：使用"none"算法伪造能通过验证的token。
	 * 待测试
	 * @throws Exception
	 */
	@Test
	public void testRSA4() throws Exception {
		
	}
}
